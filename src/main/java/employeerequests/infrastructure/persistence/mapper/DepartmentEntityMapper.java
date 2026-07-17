package employeerequests.infrastructure.persistence.mapper;

import employeerequests.domain.entity.Department;
import employeerequests.infrastructure.persistence.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentEntityMapper {
    public Department toDomain(DepartmentEntity entity) {
        if (entity == null) return null;
        return new Department(entity.getId(), entity.getName());
    }

    public DepartmentEntity toEntity(Department domain) {
        if (domain == null) return null;
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        return entity;
    }
}