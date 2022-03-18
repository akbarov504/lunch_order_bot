package uz.jl.lunchorderbot.service.complaint;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.dto.complaint.ComplaintCreateDto;
import uz.jl.lunchorderbot.dto.complaint.ComplaintDetailDto;
import uz.jl.lunchorderbot.dto.complaint.ComplaintGetDto;
import uz.jl.lunchorderbot.dto.complaint.ComplaintUpdateDto;
import uz.jl.lunchorderbot.repository.complaint.ComplaintRepository;
import uz.jl.lunchorderbot.service.AbstractService;
import uz.jl.lunchorderbot.service.GenericCRUDService;

import java.util.List;

@Service
@Component
public class ComplaintService extends AbstractService<ComplaintRepository> implements GenericCRUDService<ComplaintCreateDto, ComplaintUpdateDto, ComplaintGetDto, ComplaintDetailDto, Long> {
    public ComplaintService(ComplaintRepository repository) {
        super(repository);
    }

    @Override
    public void create(ComplaintCreateDto dto) {
        repository.create(dto);
    }

    @Override
    public void update(ComplaintUpdateDto dto) {

    }

    @Override
    public void delete(Long key) {
        repository.delete(key);
    }

    @Override
    public ComplaintGetDto get(Long key) {
        return repository.get(key);
    }

    @Override
    public ComplaintDetailDto detail(Long key) {
        return null;
    }

    @Override
    public List<ComplaintGetDto> list() {
        return repository.list();
    }

    @Override
    public Long totalCount() {
        return null;
    }

    public List<ComplaintGetDto> list(Long id, String chatId) {
        return repository.list(id, chatId);
    }

    public void delete(Long id, String chatId) {
        repository.delete(id, chatId);
    }

    public void create(ComplaintCreateDto dto, String chatId) {
        repository.create(dto, chatId);
    }
}
