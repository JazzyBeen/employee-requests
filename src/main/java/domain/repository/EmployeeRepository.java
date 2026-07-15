package domain.repository;

import domain.entity.Employee;

public interface EmployeeRepository {
    Employee findById(long id);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void delete(Employee employee);
}
