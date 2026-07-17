package employeerequests.infrastructure.persistence.repository;

import employeerequests.domain.entity.Request;
import employeerequests.domain.enums.RequestStatus;
import employeerequests.domain.repository.RequestRepository;
import employeerequests.infrastructure.persistence.entity.RequestEntity;
import employeerequests.infrastructure.persistence.mapper.RequestEntityMapper;
import employeerequests.infrastructure.persistence.specification.RequestSpecification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RequestRepositoryImpl implements RequestRepository {
    private final JpaRequestRepository jpaRepository;
    private final RequestEntityMapper mapper;

    public RequestRepositoryImpl(JpaRequestRepository jpaRepository, RequestEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Request> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Request save(Request request) {
        RequestEntity entity = mapper.toEntity(request);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Request> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Request> findByFilters(RequestStatus status, Long executorId, Long departmentId, Boolean isOverdue) {
        return jpaRepository.findAll(RequestSpecification.withFilters(status, executorId, departmentId, isOverdue))
                .stream().map(mapper::toDomain).toList();
    }
}