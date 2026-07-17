package employeerequests.infrastructure.persistence.mapper;

import employeerequests.domain.entity.Employee;
import employeerequests.infrastructure.persistence.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEntityMapper {
    private final DepartmentEntityMapper departmentEntityMapper;

    public EmployeeEntityMapper(DepartmentEntityMapper departmentEntityMapper) {
        this.departmentEntityMapper = departmentEntityMapper;
    }

    public Employee toDomain(EmployeeEntity entity) {
        if (entity == null) return null;
        return new Employee(
                entity.getId(),
                entity.getFullName(),
                entity.getPosition(),
                departmentEntityMapper.toDomain(entity.getDepartment())
        );
    }

    public EmployeeEntity toEntity(Employee domain) {
        if (domain == null) return null;
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(domain.getId());
        entity.setFullName(domain.getFullName());
        entity.setPosition(domain.getPosition());
        entity.setDepartment(departmentEntityMapper.toEntity(domain.getDepartment()));
        return entity;
    }
}
