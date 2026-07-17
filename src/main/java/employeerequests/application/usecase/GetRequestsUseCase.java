package employeerequests.application.usecase;

import employeerequests.domain.entity.Request;
import employeerequests.domain.enums.RequestStatus;
import employeerequests.domain.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetRequestsUseCase {
    private final RequestRepository requestRepository;

    public GetRequestsUseCase(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> execute(RequestStatus status, Long executorId, Long departmentId, Boolean isOverdue) {
        return requestRepository.findByFilters(status, executorId, departmentId, isOverdue);
    }
}
