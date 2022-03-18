package uz.jl.lunchorderbot.repository.user;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.dto.user.*;
import uz.jl.lunchorderbot.repository.AbstractRepository;
import uz.jl.lunchorderbot.repository.GenericCRUDRepository;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Objects;

@Component
public class UserRepository extends AbstractRepository implements GenericCRUDRepository<UserCreateDto, UserUpdateDto, UserGetDto, UserDetailDto, Long> {
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
        String url = "http://localhost:80/api/user/get/" + key + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new NotFoundException("User not found");
        }

        UserGetDto dto = gson.fromJson(response.getBody(), UserGetDto.class);
        if (Objects.isNull(dto)) {
            throw new NotFoundException("User not found");
        }
        return dto;
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
        String url = "http://localhost:80/api/user/get-by-phone/" + phone + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new NotFoundException("User not found");
        }

        UserGetDto dto = gson.fromJson(response.getBody(), UserGetDto.class);
        if (Objects.isNull(dto)) {
            throw new NotFoundException("User not found");
        }
        return dto;
    }

    public void acceptDepartment(UserAcceptDepartmentDto dto) {
        String url = "http://localhost:80/api/user/accept-department/";
        HttpEntity<UserAcceptDepartmentDto> request = new HttpEntity<>(dto, headers);

        try {
            template.exchange(url, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public UserGetDto getByChatId(Integer chatId) {
        String url = "http://localhost:80/api/user/get-by-chatId/" + chatId + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new NotFoundException("User not found");
        }

        UserGetDto dto = gson.fromJson(response.getBody(), UserGetDto.class);
        if (Objects.isNull(dto)) {
            throw new NotFoundException("User not found");
        }
        return dto;
    }
}
