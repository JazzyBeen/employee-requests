package employeerequests.presentation.controller;

import employeerequests.application.dto.request.CreateDepartmentCommand;
import employeerequests.application.dto.response.DepartmentResponse;
import employeerequests.application.mapper.DepartmentMapper;
import employeerequests.application.usecase.CreateDepartmentUseCase;
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