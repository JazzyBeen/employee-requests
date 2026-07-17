package employeerequests.infrastructure.persistence.repository;

import employeerequests.domain.entity.Employee;
import employeerequests.domain.repository.EmployeeRepository;
import employeerequests.infrastructure.persistence.entity.EmployeeEntity;
import employeerequests.infrastructure.persistence.mapper.EmployeeEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final JpaEmployeeRepository jpaRepository;
    private final EmployeeEntityMapper mapper;

    public EmployeeRepositoryImpl(JpaEmployeeRepository jpaRepository, EmployeeEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity entity = mapper.toEntity(employee);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Employee> findByDepartmentId(Long departmentId) {
        return jpaRepository.findByDepartmentId(departmentId).stream().map(mapper::toDomain).toList();
    }
}
