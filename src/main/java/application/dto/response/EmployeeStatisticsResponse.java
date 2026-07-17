package application.dto.response;

public record EmployeeStatisticsResponse(Long employeeId, String fullName, long completedRequestsCount) {}
