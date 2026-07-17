package application.usecase;

import application.dto.request.CreateRequestCommand;
import domain.entity.Employee;
import domain.entity.Request;
import domain.exception.EmployeeNotFoundException;
import domain.repository.EmployeeRepository;
import domain.repository.RequestRepository;


public class CreateRequestUseCase {
    private final EmployeeRepository employeeRepository;
    private final RequestRepository requestRepository;

    private Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(id));
    }


    public Request execute(CreateRequestCommand command) {
        Employee author = getEmployee(command.authorId());
        Employee executor = getEmployee(command.executorId());
        Request request = Request.create(
            author,
                executor,
                command.description(),
                command.deadline()
        );
       return requestRepository.save(request);
    }

    public CreateRequestUseCase(EmployeeRepository employeeRepository, RequestRepository requestRepository) {
        this.employeeRepository = employeeRepository;
        this.requestRepository = requestRepository;
    }

}
