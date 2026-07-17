package employeerequests.application.usecase;

import employeerequests.application.dto.request.CreateEmployeeCommand;
import employeerequests.domain.entity.Department;
import employeerequests.domain.entity.Employee;
import employeerequests.domain.exception.DepartmentNotFoundException;
import employeerequests.domain.repository.DepartmentRepository;
import employeerequests.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateEmployeeUseCase {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public CreateEmployeeUseCase(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee execute(CreateEmployeeCommand command) {
        Department department = departmentRepository.findById(command.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(command.departmentId()));
        Employee employee = new Employee(null, command.fullName(), command.position(), department);
        return employeeRepository.save(employee);
    }
}
