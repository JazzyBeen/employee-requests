package application.dto.response;

import domain.enums.RequestStatus;

import java.time.LocalDateTime;

public record RequestResponse(
        Long id,
        String number,
        LocalDateTime createdAt,
        EmployeeResponse author,
        EmployeeResponse executor,
        String description,
        LocalDateTime deadline,
        RequestStatus status
) {}
