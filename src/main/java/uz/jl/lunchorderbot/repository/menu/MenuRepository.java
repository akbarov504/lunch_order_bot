package uz.jl.lunchorderbot.repository.menu;

import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.configuration.map.SessionMap;
import uz.jl.lunchorderbot.dto.menu.MenuCreateDto;
import uz.jl.lunchorderbot.dto.menu.MenuDetailDto;
import uz.jl.lunchorderbot.dto.menu.MenuGetDto;
import uz.jl.lunchorderbot.dto.menu.MenuUpdateDto;
import uz.jl.lunchorderbot.repository.AbstractRepository;
import uz.jl.lunchorderbot.repository.GenericCRUDRepository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class MenuRepository extends AbstractRepository implements GenericCRUDRepository<MenuCreateDto, MenuUpdateDto, MenuGetDto, MenuDetailDto, Long> {
    private final Type type = new TypeToken<List<MenuGetDto>>() {
    }.getType();

    @Override
    public void create(MenuCreateDto dto) {
        String url = "http://localhost:80/api/menu/create/";
        HttpEntity<MenuCreateDto> request = new HttpEntity<>(dto, headers);

        try {
            template.exchange(url, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MenuUpdateDto dto) {

    }

    @Override
    public void delete(Long key) {
        String url = "http://localhost:80/api/menu/delete/" + key + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try {
            template.exchange(url, HttpMethod.DELETE, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MenuGetDto get(Long key) {
        String url = "http://localhost:80/api/menu/get/" + key + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MenuGetDto dto = gson.fromJson(response.getBody(), MenuGetDto.class);
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Menu is null");
        }
        return dto;
    }

    @Override
    public MenuDetailDto detail(Long key) {
        return null;
    }

    @Override
    public List<MenuGetDto> list() {
        String url = "http://localhost:80/api/menu/list/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<MenuGetDto> list = gson.fromJson(response.getBody(), type);
        if (Objects.isNull(list)) {
            throw new RuntimeException("Menu list is null");
        }
        return list;
    }

    @Override
    public Long totalCount() {
        return null;
    }

    public MenuGetDto getToday(String chatId) {
        String url = "http://localhost:80/api/menu/get-today/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MenuGetDto dto = gson.fromJson(response.getBody(), MenuGetDto.class);
        if (Objects.isNull(dto)) {
            throw new RuntimeException("menu is null");
        }
        return dto;
    }
}
