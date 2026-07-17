package infrastructure.persistence.repository;

import domain.entity.Department;
import domain.repository.DepartmentRepository;
import infrastructure.persistence.entity.DepartmentEntity;
import infrastructure.persistence.mapper.DepartmentEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final JpaDepartmentRepository jpaRepository;
    private final DepartmentEntityMapper mapper;

    public DepartmentRepositoryImpl(JpaDepartmentRepository jpaRepository, DepartmentEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Department> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Department save(Department department) {
        DepartmentEntity entity = mapper.toEntity(department);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Department> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
}
