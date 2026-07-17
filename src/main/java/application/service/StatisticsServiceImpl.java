package application.service;

import application.dto.response.EmployeeStatisticsResponse;
import application.dto.response.StatisticsResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final JpaRequestRepository jpaRequestRepository;

    public StatisticsServiceImpl(JpaRequestRepository jpaRequestRepository) {
        this.jpaRequestRepository = jpaRequestRepository;
    }

    @Override
    public StatisticsResponse getStatistics() {
        long total = jpaRequestRepository.count();
        List<Object[]> rawStats = jpaRequestRepository.findTopExecutors();
        List<EmployeeStatisticsResponse> top = rawStats.stream()
                .map(obj -> new EmployeeStatisticsResponse((Long) obj[0], (String) obj[1], (Long) obj[2]))
                .collect(Collectors.toList());
        return new StatisticsResponse(total, top);
    }
}
