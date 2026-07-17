package domain.exception;

public class RequestNotFoundException extends EntityNotFoundException {
    public RequestNotFoundException(Long id) {
        super("Request " + id + " not found");
    }
}
