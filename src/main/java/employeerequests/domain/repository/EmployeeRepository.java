package employeerequests.domain.repository;

import employeerequests.domain.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
    List<Employee> findAll();
    List<Employee> findByDepartmentId(Long departmentId);
}