package employeerequests.domain.repository;

import employeerequests.domain.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {
    Optional<Department> findById(Long id);
    Department save(Department department);
    void deleteById(Long id);
    List<Department> findAll();
}
