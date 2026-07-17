package employeerequests.infrastructure.persistence.repository;

import employeerequests.infrastructure.persistence.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaRequestRepository extends JpaRepository<RequestEntity, Long>, JpaSpecificationExecutor<RequestEntity> {

    @Query("SELECT r.status, COUNT(r) FROM RequestEntity r GROUP BY r.status")
    List<Object[]> countByStatus();

    @Query("SELECT COUNT(r) FROM RequestEntity r WHERE r.deadline < CURRENT_TIMESTAMP AND r.status != 'COMPLETED'")
    long countOverdue();

    @Query("SELECT r.executor.id, r.executor.fullName, COUNT(r) FROM RequestEntity r WHERE r.status = 'COMPLETED' GROUP BY r.executor.id, r.executor.fullName ORDER BY COUNT(r) DESC")
    List<Object[]> countCompletedByExecutor();
}
