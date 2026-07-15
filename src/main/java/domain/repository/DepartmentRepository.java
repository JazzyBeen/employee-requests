package domain.repository;

import domain.entity.Department;

public interface DepartmentRepository {
    Department findById(long id);
    Department save(Department department);
    Department update(Department department);
    void delete(Department department);
}
