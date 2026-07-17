package application.usecase;

import application.dto.response.StatisticsResponse;
import application.service.StatisticsService;
import org.springframework.stereotype.Service;

@Service
public class GetStatisticsUseCase {
    private final StatisticsService statisticsService;

    public GetStatisticsUseCase(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public StatisticsResponse execute() {
        return statisticsService.getStatistics();
    }
}
