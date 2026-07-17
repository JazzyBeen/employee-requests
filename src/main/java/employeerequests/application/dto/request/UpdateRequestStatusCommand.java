package employeerequests.application.dto.request;

import employeerequests.domain.enums.RequestStatus;

public record UpdateRequestStatusCommand(RequestStatus status) {}
