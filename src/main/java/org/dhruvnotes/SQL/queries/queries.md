Q.1 **(Self Join)** Problem: Find employees and their managers from the employees table, where manager_id references employee_id.
```jql
SELECT e1.first_name AS employee, e2.first_name AS manager
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.employee_id;
```

Q.2. **Filter groups** : List departments with more than 5 employees.
```jql
SELECT department_id, COUNT(*) AS employee_count
FROM employees
GROUP BY department_id
HAVING COUNT(*) > 5;
```

Q.3 **Subquery** : Find employees with salaries greater than the average salary.
```jql
SELECT first_name, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);
```

Q.4 **Subquery** : Find the department with the highest average salary.
```jql
SELECT department_id, avg_salary
FROM (
    SELECT department_id, AVG(salary) AS avg_salary
    FROM employees
    GROUP BY department_id
) dept_salaries
ORDER BY avg_salary DESC
LIMIT 1;
```

Q.5 **Correlated Subquery** : Find employees who earn more than the average salary in their department.
```jql
SELECT first_name, salary, department_id
FROM employees e1
WHERE salary > (
    SELECT AVG(salary)
    FROM employees e2
    WHERE e2.department_id = e1.department_id
);
```
* The subquery is correlated, so it's evaluated per row with the current department.
* No GROUP BY is needed because you're filtering to one department per subquery execution.
  the inner (sub)query runs once for each row in the outer query. This is because it's a correlated subquery — it depends on the current row of the outer query.

Q.6 **Find duplicate records** : Identify employees with duplicate email addresses.
```jql
SELECT email, COUNT(*) AS email_count
FROM employees
GROUP BY email
HAVING COUNT(*) > 1;
```

Q.7 **Top N per group, Window Function**: Fetch the top 3 highest-paid employees in each department.
```jql
SELECT first_name, salary, department_id
FROM (
    SELECT first_name, salary, department_id,
           ROW_NUMBER() OVER (PARTITION BY department_id ORDER BY salary DESC) AS rn
    FROM employees
) ranked
WHERE rn <= 3;
```

Q.8 **Nth Highest Salary (Multiple Solutions)**

_**Solution 1: Using LIMIT and OFFSET**_
```jql
-- For N=2:
SELECT DISTINCT salary
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET 1;
```

**_Solution 2: Using DENSE_RANK()_**

Dense Rank handles the ties in rank, also ranks are not skipped in case of `DENSE_RANK()` unlike `RANK()` which will be more useful in cases like lead-boards.
```jql
SELECT salary
FROM (
    SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
    FROM employees
) ranked
WHERE rnk = N;
```
