package employeerequests.application.mapper;

import employeerequests.application.dto.response.DepartmentResponse;
import employeerequests.domain.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public DepartmentResponse toResponse(Department department) {
        if (department == null) return null;
        return new DepartmentResponse(department.getId(), department.getName());
    }
}
