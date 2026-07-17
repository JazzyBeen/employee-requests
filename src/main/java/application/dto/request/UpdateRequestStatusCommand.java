package application.dto.request;

import domain.enums.RequestStatus;

public record UpdateRequestStatusCommand(RequestStatus status) {}
