package infrastructure.persistence.mapper;

import domain.entity.Request;
import infrastructure.persistence.entity.RequestEntity;
import org.springframework.stereotype.Component;

@Component
public class RequestEntityMapper {
    private final EmployeeEntityMapper employeeEntityMapper;

    public RequestEntityMapper(EmployeeEntityMapper employeeEntityMapper) {
        this.employeeEntityMapper = employeeEntityMapper;
    }

    public Request toDomain(RequestEntity entity) {
        if (entity == null) return null;
        return new Request(
                entity.getId(),
                entity.getNumber(),
                entity.getCreatedAt(),
                employeeEntityMapper.toDomain(entity.getAuthor()),
                employeeEntityMapper.toDomain(entity.getExecutor()),
                entity.getDescription(),
                entity.getDeadline(),
                entity.getStatus()
        );
    }

    public RequestEntity toEntity(Request domain) {
        if (domain == null) return null;
        RequestEntity entity = new RequestEntity();
        entity.setId(domain.getId());
        entity.setNumber(domain.getNumber());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setAuthor(employeeEntityMapper.toEntity(domain.getAuthor()));
        entity.setExecutor(employeeEntityMapper.toEntity(domain.getExecutor()));
        entity.setDescription(domain.getDescription());
        entity.setDeadline(domain.getDeadline());
        entity.setStatus(domain.getStatus());
        return entity;
    }
}
