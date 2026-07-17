package employeerequests.infrastructure.persistence.specification;


import employeerequests.domain.enums.RequestStatus;
import employeerequests.infrastructure.persistence.entity.EmployeeEntity;
import employeerequests.infrastructure.persistence.entity.RequestEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestSpecification {

    public static Specification<RequestEntity> withFilters(RequestStatus status, Long executorId, Long departmentId, Boolean isOverdue) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (executorId != null) {
                predicates.add(cb.equal(root.get("executor").get("id"), executorId));
            }
            if (departmentId != null) {
                Join<RequestEntity, EmployeeEntity> executorJoin = root.join("executor");
                predicates.add(cb.equal(executorJoin.get("department").get("id"), departmentId));
            }
            if (Boolean.TRUE.equals(isOverdue)) {
                predicates.add(cb.lessThan(root.get("deadline"), LocalDateTime.now()));
                predicates.add(cb.notEqual(root.get("status"), RequestStatus.COMPLETED));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
