package employeerequests.application.usecase;

import employeerequests.application.dto.request.CreateRequestCommand;
import employeerequests.domain.entity.Employee;
import employeerequests.domain.entity.Request;
import employeerequests.domain.exception.EmployeeNotFoundException;
import employeerequests.domain.repository.EmployeeRepository;
import employeerequests.domain.repository.RequestRepository;
import employeerequests.domain.service.RequestNumberGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateRequestUseCase {
    private final EmployeeRepository employeeRepository;
    private final RequestRepository requestRepository;
    private final RequestNumberGenerator requestNumberGenerator;

    public CreateRequestUseCase(EmployeeRepository employeeRepository, RequestRepository requestRepository, RequestNumberGenerator requestNumberGenerator) {
        this.employeeRepository = employeeRepository;
        this.requestRepository = requestRepository;
        this.requestNumberGenerator = requestNumberGenerator;
    }

    private Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Request execute(CreateRequestCommand command) {
        Employee author = getEmployee(command.authorId());
        Employee executor = getEmployee(command.executorId());
        Request request = Request.create(
                requestNumberGenerator.generate(),
                author,
                executor,
                command.description(),
                command.deadline()
        );
        return requestRepository.save(request);
    }
}
