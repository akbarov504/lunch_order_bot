package uz.jl.lunchorderbot.service.auth;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.dto.auth.AuthLoginDto;
import uz.jl.lunchorderbot.dto.user.UserCreateDto;
import uz.jl.lunchorderbot.repository.auth.AuthRepository;
import uz.jl.lunchorderbot.service.AbstractService;

@Service
@Component
public class AuthService extends AbstractService<AuthRepository> {
    public AuthService(AuthRepository repository) {
        super(repository);
    }

    public void login(AuthLoginDto dto, String chatId) {
        repository.login(dto, chatId);
    }

    public void register(UserCreateDto dto) {
        repository.register(dto);
    }
}
