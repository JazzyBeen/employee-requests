package employeerequests.application.dto.response;

import employeerequests.domain.enums.RequestStatus;

import java.util.List;
import java.util.Map;

public record StatisticsResponse(
        Map<RequestStatus, Long> requestsByStatus,
        long overdueRequestsCount,
        List<EmployeeStatisticsResponse> completedByExecutor
) {}
