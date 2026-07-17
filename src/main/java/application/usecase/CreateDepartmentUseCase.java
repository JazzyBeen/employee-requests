package application.usecase;

import application.dto.request.CreateDepartmentCommand;
import domain.entity.Department;
import domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateDepartmentUseCase {
    private final DepartmentRepository departmentRepository;

    public CreateDepartmentUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department execute(CreateDepartmentCommand command) {
        Department department = new Department(null, command.name());
        return departmentRepository.save(department);
    }
}