package employeerequests.infrastructure.persistence.repository;

import employeerequests.infrastructure.persistence.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
