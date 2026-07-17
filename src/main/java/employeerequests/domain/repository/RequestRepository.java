package employeerequests.domain.repository;

import employeerequests.domain.entity.Request;
import employeerequests.domain.enums.RequestStatus;

import java.util.List;
import java.util.Optional;

public interface RequestRepository {
    Optional<Request> findById(Long id);
    Request save(Request request);
    void deleteById(Long id);
    List<Request> findAll();
    List<Request> findByFilters(RequestStatus status, Long executorId, Long departmentId, Boolean isOverdue);
}
