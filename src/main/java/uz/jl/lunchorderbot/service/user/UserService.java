package uz.jl.lunchorderbot.service.user;

import org.jvnet.hk2.annotations.Service;
import uz.jl.lunchorderbot.dto.user.*;
import uz.jl.lunchorderbot.repository.user.UserRepository;
import uz.jl.lunchorderbot.service.AbstractService;
import uz.jl.lunchorderbot.service.GenericCRUDService;

import java.util.List;

@Service
public class UserService extends AbstractService<UserRepository> implements GenericCRUDService<UserCreateDto, UserUpdateDto, UserGetDto, UserDetailDto, Long> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    public void create(UserCreateDto dto) {

    }

    @Override
    public void update(UserUpdateDto dto) {

    }

    @Override
    public void delete(Long key) {

    }

    @Override
    public UserGetDto get(Long key) {
        return repository.get(key);
    }

    @Override
    public UserDetailDto detail(Long key) {
        return null;
    }

    @Override
    public List<UserGetDto> list() {
        return null;
    }

    @Override
    public Long totalCount() {
        return null;
    }

    public UserGetDto getByPhoneNumber(String phone) {
        return repository.getByPhoneNumber(phone);
    }

    public void acceptDepartment(UserAcceptDepartmentDto dto) {
        repository.acceptDepartment(dto);
    }

    public UserGetDto getByChatId(Integer chatId) {
        return repository.getByChatId(chatId);
    }
}
