package presentation.controller;

import application.dto.request.CreateEmployeeCommand;
import application.dto.response.EmployeeResponse;
import application.mapper.EmployeeMapper;
import application.usecase.CreateEmployeeUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(CreateEmployeeUseCase createEmployeeUseCase, EmployeeMapper employeeMapper) {
        this.createEmployeeUseCase = createEmployeeUseCase;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping
    public EmployeeResponse create(@RequestBody CreateEmployeeCommand command) {
        return employeeMapper.toResponse(createEmployeeUseCase.execute(command));
    }
}
