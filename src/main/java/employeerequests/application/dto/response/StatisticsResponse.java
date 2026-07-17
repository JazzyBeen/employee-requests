package employeerequests.application.dto.response;

import java.util.List;

public record StatisticsResponse(long totalRequests, List<EmployeeStatisticsResponse> topExecutors) {}
