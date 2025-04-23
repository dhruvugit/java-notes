### Optimistic Locking in Spring Boot
Optimistic locking is simpler to implement in Spring Boot using JPA's `@Version` annotation. It relies on a version column in the database to detect conflicts. If another transaction modifies the entity before your transaction commits, an `OptimisticLockException` is thrown.


### Overview of Locking Mechanisms
- **Pessimistic Locking**: Assumes conflicts are likely, so it locks resources (e.g., database rows) during a transaction to prevent other transactions from modifying them. This is typically achieved using database-level locks (e.g., `SELECT ... FOR UPDATE`).
- **Optimistic Locking**: Assumes conflicts are rare, allowing concurrent access but checking for conflicts at commit time. It often uses a version column to detect changes made by other transactions.


#### Example: Optimistic Locking
This example demonstrates optimistic locking for updating a product's stock, using a version column to track changes.

```x-java
package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.OptimisticLockException;

@Service
public class OptimisticLockingService {

    private final ProductRepository productRepository;

    public OptimisticLockingService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product updateProductStock(Long productId, int quantity) {
        try {
            // Find product (no explicit lock)
            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
            
            // Simulate business logic
            if (product.getStock() >= quantity) {
                product.setStock(product.getStock() - quantity);
                return productRepository.save(product);
            } else {
                throw new RuntimeException("Insufficient stock");
            }
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Concurrent modification detected", e);
        }
    }
}
```

#### Entity with Version Column
The `Product` entity includes a `@Version` field for optimistic locking.

```x-java
package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Version;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;

    @Version
    private Long version;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}
```

#### Repository for Optimistic Locking
The repository remains standard, as optimistic locking is handled by JPA's version management.

```x-java
package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

#### Notes on Optimistic Locking
- **Version Column**: The `@Version` annotation creates a column (e.g., `version`) that JPA increments on each update. If the version in the database doesn't match the entity's version at commit time, an `OptimisticLockException` is thrown.
- **Handling Conflicts**: Catch `OptimisticLockException` and retry the operation or inform the user of the conflict.
- **Performance**: Optimistic locking is more performant than pessimistic locking for read-heavy scenarios, as it avoids locking rows during the transaction.
- **Database Support**: Most databases support versioning via a numeric or timestamp column.

### Key Differences
| Feature                | Pessimistic Locking                          | Optimistic Locking                          |
|------------------------|----------------------------------------------|---------------------------------------------|
| **Locking Mechanism**  | Database-level locks (e.g., `FOR UPDATE`)    | Version column check at commit             |
| **Use Case**           | High contention, critical data integrity     | Low contention, read-heavy operations      |
| **Performance**        | Slower due to locks                          | Faster, no locks during transaction        |
| **Implementation**     | `@Lock`, `FOR UPDATE` queries                | `@Virgin` annotation                       |
| **Error Handling**     | Deadlocks, lock timeouts                     | `OptimisticLockException`                  |

### Does `@Transactional` with Isolation Enable Pessimistic Locking?
- **Short Answer**: No, `@Transactional` with an isolation level (e.g., `SERIALIZABLE`) does not automatically enable pessimistic locking. It controls transaction isolation but doesn't apply row-level locks like `FOR UPDATE`.
- **Details**:
    - Isolation levels like `SERIALIZABLE` or `REPEATABLE_READ` define how transactions see data changes but rely on the database's default locking strategy (often shared locks for reads).
    - To enforce pessimistic locking, you must explicitly use `@Lock(LockModeType.PESSIMISTIC_WRITE)` or a query with `FOR UPDATE`.
    - Example: `@Transactional(isolation = Isolation.SERIALIZABLE)` may cause the database to use stricter locking internally, but it won't guarantee exclusive row locks unless you specify a pessimistic lock mode.

### Best Practices
- **Pessimistic Locking**:
    - Use for short transactions to minimize lock duration.
    - Avoid in high-concurrency systems to prevent bottlenecks.
    - Test for deadlocks and configure lock timeouts in your database.
- **Optimistic Locking**:
    - Ideal for applications with infrequent conflicts.
    - Ensure proper error handling for `OptimisticLockException`.
    - Use a numeric version column (e.g., `Long` or `Integer`) for simplicity.
- **General**:
    - Choose the locking strategy based on your application's concurrency needs.
    - Test locking behavior under load to ensure scalability.
    - Use Spring's `@Transactional` to manage transaction boundaries, but be explicit about locking mechanisms.

### Dependencies
Add the following dependencies to your `pom.xml` for Spring Boot with JPA:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <!-- Add your preferred database driver, e.g., MySQL or PostgreSQL -->
</dependencies>
```


