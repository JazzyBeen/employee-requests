package application.usecase;

import application.dto.request.UpdateRequestStatusCommand;
import domain.entity.Request;
import domain.exception.RequestNotFoundException;
import domain.repository.RequestRepository;
import domain.service.RequestStatusValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateRequestStatusUseCase {
    private final RequestRepository requestRepository;
    private final RequestStatusValidator statusValidator;

    public UpdateRequestStatusUseCase(RequestRepository requestRepository, RequestStatusValidator statusValidator) {
        this.requestRepository = requestRepository;
        this.statusValidator = statusValidator;
    }

    public Request execute(Long requestId, UpdateRequestStatusCommand command) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(requestId));
        statusValidator.validate(request.getStatus(), command.status());
        request.updateStatus(command.status());
        return requestRepository.save(request);
    }
}
