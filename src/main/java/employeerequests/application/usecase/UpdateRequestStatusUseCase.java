package employeerequests.application.usecase;

import employeerequests.application.dto.request.UpdateRequestStatusCommand;
import employeerequests.domain.entity.Request;
import employeerequests.domain.exception.RequestNotFoundException;
import employeerequests.domain.repository.RequestRepository;
import employeerequests.domain.service.RequestStatusValidator;
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
