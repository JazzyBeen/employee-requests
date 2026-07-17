package employeerequests.presentation.controller;

import employeerequests.application.dto.request.CreateEmployeeCommand;
import employeerequests.application.dto.response.EmployeeResponse;
import employeerequests.application.mapper.EmployeeMapper;
import employeerequests.application.usecase.CreateEmployeeUseCase;
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
