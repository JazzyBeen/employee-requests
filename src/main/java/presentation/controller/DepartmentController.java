package presentation.controller;

import application.dto.request.CreateDepartmentCommand;
import application.dto.response.DepartmentResponse;
import application.mapper.DepartmentMapper;
import application.usecase.CreateDepartmentUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final DepartmentMapper departmentMapper;

    public DepartmentController(CreateDepartmentUseCase createDepartmentUseCase, DepartmentMapper departmentMapper) {
        this.createDepartmentUseCase = createDepartmentUseCase;
        this.departmentMapper = departmentMapper;
    }

    @PostMapping
    public DepartmentResponse create(@RequestBody CreateDepartmentCommand command) {
        return departmentMapper.toResponse(createDepartmentUseCase.execute(command));
    }
}