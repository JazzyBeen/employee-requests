package employeerequests.application.dto.request;

public record CreateEmployeeCommand(String fullName, String position, Long departmentId) {}
