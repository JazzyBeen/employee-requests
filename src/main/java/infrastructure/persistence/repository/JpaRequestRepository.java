package infrastructure.persistence.repository;

import infrastructure.persistence.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaRequestRepository extends JpaRepository<RequestEntity, Long>, JpaSpecificationExecutor<RequestEntity> {
    @Query("SELECT r.executor.id, r.executor.fullName, COUNT(r) FROM RequestEntity r WHERE r.status = 'COMPLETED' GROUP BY r.executor.id, r.executor.fullName ORDER BY COUNT(r) DESC LIMIT 10")
    List<Object[]> findTopExecutors();
}
