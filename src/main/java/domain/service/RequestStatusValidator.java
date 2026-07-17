package domain.service;

import domain.enums.RequestStatus;
import domain.exception.IllegalStatusTransitionException;
import org.springframework.stereotype.Service;

@Service
public class RequestStatusValidator {
    public void validate(RequestStatus current, RequestStatus target) {
        if (current == RequestStatus.NEW && target == RequestStatus.COMPLETED) {
            throw new IllegalStatusTransitionException("Cannot transition from NEW to COMPLETED");
        }
        if (current == RequestStatus.COMPLETED) {
            throw new IllegalStatusTransitionException("Request is already COMPLETED");
        }
    }
}
