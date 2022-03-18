package uz.jl.lunchorderbot.repository.auth;

import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.configuration.map.SessionMap;
import uz.jl.lunchorderbot.dto.auth.AuthLoginDto;
import uz.jl.lunchorderbot.dto.token.TokenGetDto;
import uz.jl.lunchorderbot.dto.user.UserCreateDto;
import uz.jl.lunchorderbot.enums.token.TokenEnum;
import uz.jl.lunchorderbot.repository.AbstractRepository;
import uz.jl.lunchorderbot.repository.BaseRepository;

import javax.ws.rs.NotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class AuthRepository extends AbstractRepository implements BaseRepository {
    private final Type type = new TypeToken<List<TokenGetDto>>() {
    }.getType();

    public void login(AuthLoginDto dto, String chatId) {
        String url = "http://localhost:80/api/auth/login/";
        HttpEntity<AuthLoginDto> request = new HttpEntity<>(dto, headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            throw new NotFoundException("User not found");
        }

        List<TokenGetDto> tokens = gson.fromJson(response.getBody(), type);

        if (Objects.isNull(tokens)) {
            throw new NotFoundException("User not found");
        }

        for (TokenGetDto token : tokens) {
            if (token.getType().equals(TokenEnum.ACCESS_TOKEN.name())) {
                SessionMap.SESSION_MAP.put(chatId, token.getToken());
                return;
            }
        }
    }

    public void register(UserCreateDto dto) {
        String url = "http://localhost:80/api/auth/register/";
        HttpEntity<UserCreateDto> request = new HttpEntity<>(dto, headers);

        try {
            template.exchange(url, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
