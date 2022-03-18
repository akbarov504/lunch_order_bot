package uz.jl.lunchorderbot.service.department;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.dto.department.DepartmentCreateDto;
import uz.jl.lunchorderbot.dto.department.DepartmentDetailDto;
import uz.jl.lunchorderbot.dto.department.DepartmentGetDto;
import uz.jl.lunchorderbot.dto.department.DepartmentUpdateDto;
import uz.jl.lunchorderbot.repository.department.DepartmentRepository;
import uz.jl.lunchorderbot.service.AbstractService;
import uz.jl.lunchorderbot.service.GenericCRUDService;

import java.util.List;

@Service
@Component
public class DepartmentService extends AbstractService<DepartmentRepository> implements GenericCRUDService<DepartmentCreateDto, DepartmentUpdateDto, DepartmentGetDto, DepartmentDetailDto, Long> {
    public DepartmentService(DepartmentRepository repository) {
        super(repository);
    }

    @Override
    public void create(DepartmentCreateDto dto) {
        repository.create(dto);
    }

    @Override
    public void update(DepartmentUpdateDto dto) {
        repository.update(dto);
    }

    @Override
    public void delete(Long key) {
        repository.delete(key);
    }

    @Override
    public DepartmentGetDto get(Long key) {
        return repository.get(key);
    }

    @Override
    public DepartmentDetailDto detail(Long key) {
        return repository.detail(key);
    }

    @Override
    public List<DepartmentGetDto> list() {
        return repository.list();
    }

    @Override
    public Long totalCount() {
        return repository.totalCount();
    }

    public DepartmentGetDto getByName(String name) {
        return repository.getByName(name);
    }
}
