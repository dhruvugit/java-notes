### Pessimistic Locking in Spring Boot
Pessimistic locking can be implemented using JPA (Java Persistence API) with Spring Data JPA, leveraging `@Lock` annotations or explicit queries. The `@Transactional` annotation with an appropriate isolation level (e.g., `SERIALIZABLE` or `REPEATABLE_READ`) can help, but it alone doesn't enforce pessimistic locking. You need to explicitly specify the lock mode (e.g., `PESSIMISTIC_WRITE`) to acquire exclusive locks.

#### Using `@Transactional` and Isolation
- The `@Transactional` annotation with an isolation level like `SERIALIZABLE` ensures strict transaction isolation but doesn't inherently apply a pessimistic lock (e.g., `FOR UPDATE`). It controls how transactions interact but relies on the database's default locking behavior unless you explicitly request a lock.
- To achieve pessimistic locking, you typically combine `@Transactional` with `@Lock` or a custom query using `FOR UPDATE`.

If you want to do manual pessimistic then do (use `FOR UPDATE`): 
```text
@Query(value = "SELECT * FROM user_profile WHERE id = :id FOR UPDATE", nativeQuery = true)
UserProfile findUserProfileForUpdate(@Param("id") Long id);
```

#### Example: Pessimistic Locking with `@Lock`
This example shows how to use pessimistic locking to lock a row when updating an entity, 



```x-java
package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;

@Service
public class PessimisticLockingService {

    private final ProductRepository productRepository;

    public PessimisticLockingService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Product updateProductStock(Long productId, int quantity) {
        // Find product with pessimistic lock (FOR UPDATE)
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        // Simulate business logic
        if (product.getStock() >= quantity) {
            product.setStock(product.getStock() - quantity);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Insufficient stock");
        }
    }
}
```

#### Repository for Pessimistic Locking
The repository uses Spring Data JPA and supports the `@Lock` annotation.

```x-java
package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(Long id);
}
```

#### Entity for Pessimistic Locking
The `Product` entity is a simple JPA entity.

```x-java
package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
```

#### Notes on Pessimistic Locking
- **Lock Modes**:
    - `PESSIMISTIC_WRITE`: Acquires an exclusive lock (e.g., `FOR UPDATE` in SQL). Prevents other transactions from reading or writing the locked rows.
    - `PESSIMISTIC_READ`: Acquires a shared lock (less common, e.g., `FOR SHARE` in some databases). Allows reading but prevents writing.
- **Isolation Levels**:
    - Using `@Transactional(isolation = Isolation.SERIALIZABLE)` can complement pessimistic locking by ensuring strict isolation, but it may not apply `FOR UPDATE` unless explicitly requested.
    - Common isolation levels like `READ_COMMITTED` or `REPEATABLE_READ` may not suffice for pessimistic locking without `@Lock`.
- **Database Support**: Ensure your database (e.g., PostgreSQL, MySQL) supports `FOR UPDATE`. Most relational databases do.
- **Deadlocks**: Pessimistic locking can lead to deadlocks if multiple transactions lock resources in different orders. Use consistent locking orders to minimize this risk.