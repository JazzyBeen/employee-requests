package infrastructure.persistence.repository;

import infrastructure.persistence.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
