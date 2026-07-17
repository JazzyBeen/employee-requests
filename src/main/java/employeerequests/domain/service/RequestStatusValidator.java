package employeerequests.domain.service;

import employeerequests.domain.enums.RequestStatus;
import employeerequests.domain.exception.IllegalStatusTransitionException;
import org.springframework.stereotype.Service;

@Service
public class RequestStatusValidator {
    public void validate(RequestStatus current, RequestStatus target) {
        if (current == target) {
            throw new IllegalStatusTransitionException("Request is already in status " + current);
        }
        if (current == RequestStatus.NEW && target == RequestStatus.COMPLETED) {
            throw new IllegalStatusTransitionException("Cannot transition from NEW directly to COMPLETED");
        }
        if (current == RequestStatus.COMPLETED) {
            throw new IllegalStatusTransitionException("Request is already COMPLETED and cannot be changed");
        }
    }
}
