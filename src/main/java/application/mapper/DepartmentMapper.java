package application.mapper;

import application.dto.response.DepartmentResponse;
import domain.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public DepartmentResponse toResponse(Department department) {
        if (department == null) return null;
        return new DepartmentResponse(department.getId(), department.getName());
    }
}
