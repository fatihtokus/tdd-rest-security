package hello;

import org.springframework.data.repository.CrudRepository;

public interface CustomAuditEventRepository extends CrudRepository<CustomAuditEvent, Long> {
}
