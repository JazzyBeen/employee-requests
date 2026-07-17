package application.mapper;

import application.dto.response.RequestResponse;
import domain.entity.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    private final EmployeeMapper employeeMapper;

    public RequestMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public RequestResponse toResponse(Request request) {
        if (request == null) return null;
        return new RequestResponse(
                request.getId(),
                request.getNumber(),
                request.getCreatedAt(),
                employeeMapper.toResponse(request.getAuthor()),
                employeeMapper.toResponse(request.getExecutor()),
                request.getDescription(),
                request.getDeadline(),
                request.getStatus()
        );
    }
}
