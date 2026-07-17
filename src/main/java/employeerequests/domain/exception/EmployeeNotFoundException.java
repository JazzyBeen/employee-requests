package employeerequests.domain.exception;

public class EmployeeNotFoundException extends EntityNotFoundException {
    public EmployeeNotFoundException(Long id) {
        super("Employee " + id + " not found");
    }
}
