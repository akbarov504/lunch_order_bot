package uz.jl.lunchorderbot.service.menu;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.dto.menu.MenuCreateDto;
import uz.jl.lunchorderbot.dto.menu.MenuDetailDto;
import uz.jl.lunchorderbot.dto.menu.MenuGetDto;
import uz.jl.lunchorderbot.dto.menu.MenuUpdateDto;
import uz.jl.lunchorderbot.repository.menu.MenuRepository;
import uz.jl.lunchorderbot.service.AbstractService;
import uz.jl.lunchorderbot.service.GenericCRUDService;

import java.util.List;

@Service
@Component
public class MenuService extends AbstractService<MenuRepository> implements GenericCRUDService<MenuCreateDto, MenuUpdateDto, MenuGetDto, MenuDetailDto, Long> {
    public MenuService(MenuRepository repository) {
        super(repository);
    }

    @Override
    public void create(MenuCreateDto dto) {
        repository.create(dto);
    }

    @Override
    public void update(MenuUpdateDto dto) {
        repository.update(dto);
    }

    @Override
    public void delete(Long key) {
        repository.delete(key);
    }

    @Override
    public MenuGetDto get(Long key) {
        return repository.get(key);
    }

    @Override
    public MenuDetailDto detail(Long key) {
        return null;
    }

    @Override
    public List<MenuGetDto> list() {
        return repository.list();
    }

    @Override
    public Long totalCount() {
        return null;
    }

    public MenuGetDto getToday(String chatId) {
        return repository.getToday(chatId);
    }
}
