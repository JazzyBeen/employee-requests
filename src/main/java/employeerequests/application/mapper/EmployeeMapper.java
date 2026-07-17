package employeerequests.application.mapper;

import employeerequests.application.dto.response.EmployeeResponse;
import employeerequests.domain.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    private final DepartmentMapper departmentMapper;

    public EmployeeMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public EmployeeResponse toResponse(Employee employee) {
        if (employee == null) return null;
        return new EmployeeResponse(
                employee.getId(),
                employee.getFullName(),
                employee.getPosition(),
                departmentMapper.toResponse(employee.getDepartment())
        );
    }
}
