package domain.exception;

public class DepartmentNotFoundException extends EntityNotFoundException {
    public DepartmentNotFoundException(Long id) {
        super("Department " + id + " not found");
    }
}