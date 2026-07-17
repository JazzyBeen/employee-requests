package employeerequests.infrastructure.persistence.repository;

import employeerequests.infrastructure.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByDepartmentId(Long departmentId);
}
