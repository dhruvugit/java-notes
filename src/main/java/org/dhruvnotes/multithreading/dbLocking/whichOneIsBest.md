### Conclusion
- **Pessimistic Locking**: Use `@Lock(LockModeType.PESSIMISTIC_WRITE)` or `FOR UPDATE` queries within `@Transactional` to lock rows explicitly. Isolation levels alone don't suffice.
- **Optimistic Locking**: Use `@Version` for lightweight conflict detection, suitable for low-contention scenarios.
- Both approaches require careful design to balance performance and data integrity.