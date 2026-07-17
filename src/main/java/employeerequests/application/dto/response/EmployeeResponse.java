package employeerequests.application.dto.response;

public record EmployeeResponse(Long id, String fullName, String position, DepartmentResponse department) {}
