package uz.jl.lunchorderbot.service.meal;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.dto.meal.MealCreateDto;
import uz.jl.lunchorderbot.dto.meal.MealDetailDto;
import uz.jl.lunchorderbot.dto.meal.MealGetDto;
import uz.jl.lunchorderbot.dto.meal.MealUpdateDto;
import uz.jl.lunchorderbot.repository.meal.MealRepository;
import uz.jl.lunchorderbot.service.AbstractService;
import uz.jl.lunchorderbot.service.GenericCRUDService;

import java.util.List;

@Service
@Component
public class MealService extends AbstractService<MealRepository> implements GenericCRUDService<MealCreateDto, MealUpdateDto, MealGetDto, MealDetailDto, Long> {
    public MealService(MealRepository repository) {
        super(repository);
    }

    @Override
    public void create(MealCreateDto dto) {
        repository.create(dto);
    }

    @Override
    public void update(MealUpdateDto dto) {
        repository.update(dto);
    }

    @Override
    public void delete(Long key) {
        repository.delete(key);
    }

    @Override
    public MealGetDto get(Long key) {
        return repository.get(key);
    }

    public MealGetDto get(Long id, String chatId) {
        return repository.get(id, chatId);
    }

    @Override
    public MealDetailDto detail(Long key) {
        return null;
    }

    @Override
    public List<MealGetDto> list() {
        return repository.list();
    }

    public List<MealGetDto> list(String chatId) {
        return repository.list(chatId);
    }

    @Override
    public Long totalCount() {
        return null;
    }

    public MealGetDto getByName(String mealName, String chatId) {
        return repository.getByName(mealName, chatId);
    }
}
