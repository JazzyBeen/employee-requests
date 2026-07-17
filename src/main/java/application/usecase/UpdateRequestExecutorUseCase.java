package application.usecase;

import application.dto.request.UpdateRequestExecutorCommand;
import domain.entity.Employee;
import domain.entity.Request;
import domain.exception.EmployeeNotFoundException;
import domain.exception.RequestNotFoundException;
import domain.repository.EmployeeRepository;
import domain.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateRequestExecutorUseCase {
    private final RequestRepository requestRepository;
    private final EmployeeRepository employeeRepository;

    public UpdateRequestExecutorUseCase(RequestRepository requestRepository, EmployeeRepository employeeRepository) {
        this.requestRepository = requestRepository;
        this.employeeRepository = employeeRepository;
    }

    public Request execute(Long requestId, UpdateRequestExecutorCommand command) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(requestId));
        Employee executor = employeeRepository.findById(command.executorId())
                .orElseThrow(() -> new EmployeeNotFoundException(command.executorId()));
        request.updateExecutor(executor);
        return requestRepository.save(request);
    }
}