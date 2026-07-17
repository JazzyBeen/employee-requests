package employeerequests.presentation.controller;

import employeerequests.application.dto.request.CreateRequestCommand;
import employeerequests.application.dto.request.UpdateRequestExecutorCommand;
import employeerequests.application.dto.request.UpdateRequestStatusCommand;
import employeerequests.application.dto.response.RequestResponse;
import employeerequests.application.dto.response.StatisticsResponse;
import employeerequests.application.mapper.RequestMapper;
import employeerequests.application.usecase.*;
import employeerequests.domain.enums.RequestStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    private final CreateRequestUseCase createRequestUseCase;
    private final UpdateRequestStatusUseCase updateRequestStatusUseCase;
    private final UpdateRequestExecutorUseCase updateRequestExecutorUseCase;
    private final GetRequestsUseCase getRequestsUseCase;
    private final GetStatisticsUseCase getStatisticsUseCase;
    private final RequestMapper requestMapper;

    public RequestController(
            CreateRequestUseCase createRequestUseCase,
            UpdateRequestStatusUseCase updateRequestStatusUseCase,
            UpdateRequestExecutorUseCase updateRequestExecutorUseCase,
            GetRequestsUseCase getRequestsUseCase,
            GetStatisticsUseCase getStatisticsUseCase,
            RequestMapper requestMapper) {
        this.createRequestUseCase = createRequestUseCase;
        this.updateRequestStatusUseCase = updateRequestStatusUseCase;
        this.updateRequestExecutorUseCase = updateRequestExecutorUseCase;
        this.getRequestsUseCase = getRequestsUseCase;
        this.getStatisticsUseCase = getStatisticsUseCase;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    public RequestResponse create(@RequestBody CreateRequestCommand command) {
        return requestMapper.toResponse(createRequestUseCase.execute(command));
    }

    @PatchMapping("/{id}/status")
    public RequestResponse updateStatus(@PathVariable Long id, @RequestBody UpdateRequestStatusCommand command) {
        return requestMapper.toResponse(updateRequestStatusUseCase.execute(id, command));
    }

    @PatchMapping("/{id}/executor")
    public RequestResponse updateExecutor(@PathVariable Long id, @RequestBody UpdateRequestExecutorCommand command) {
        return requestMapper.toResponse(updateRequestExecutorUseCase.execute(id, command));
    }

    @GetMapping
    public List<RequestResponse> getRequests(
            @RequestParam(required = false) RequestStatus status,
            @RequestParam(required = false) Long executorId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Boolean overdue,
            @RequestParam(required = false) String sortBy
    ) {
        return getRequestsUseCase.execute(status, executorId, departmentId, overdue, sortBy).stream()
                .map(requestMapper::toResponse).toList();
    }

    @GetMapping("/statistics")
    public StatisticsResponse getStatistics() {
        return getStatisticsUseCase.execute();
    }
}