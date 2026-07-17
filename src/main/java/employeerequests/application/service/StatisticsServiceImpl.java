package employeerequests.application.service;

import employeerequests.application.dto.response.EmployeeStatisticsResponse;
import employeerequests.application.dto.response.StatisticsResponse;
import employeerequests.domain.enums.RequestStatus;
import employeerequests.infrastructure.persistence.repository.JpaRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final JpaRequestRepository jpaRequestRepository;

    public StatisticsServiceImpl(JpaRequestRepository jpaRequestRepository) {
        this.jpaRequestRepository = jpaRequestRepository;
    }

    @Override
    public StatisticsResponse getStatistics() {
        List<Object[]> statusCounts = jpaRequestRepository.countByStatus();
        Map<RequestStatus, Long> byStatus = statusCounts.stream()
                .collect(Collectors.toMap(
                        obj -> (RequestStatus) obj[0],
                        obj -> (Long) obj[1]
                ));

        long overdue = jpaRequestRepository.countOverdue();

        List<Object[]> rawStats = jpaRequestRepository.countCompletedByExecutor();
        List<EmployeeStatisticsResponse> completedByExecutor = rawStats.stream()
                .map(obj -> new EmployeeStatisticsResponse((Long) obj[0], (String) obj[1], (Long) obj[2]))
                .collect(Collectors.toList());

        return new StatisticsResponse(byStatus, overdue, completedByExecutor);
    }
}
