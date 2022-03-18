package uz.jl.lunchorderbot.repository.meal;

import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.jl.lunchorderbot.configuration.map.SessionMap;
import uz.jl.lunchorderbot.dto.department.DepartmentGetDto;
import uz.jl.lunchorderbot.dto.department.DepartmentUpdateDto;
import uz.jl.lunchorderbot.dto.meal.MealCreateDto;
import uz.jl.lunchorderbot.dto.meal.MealDetailDto;
import uz.jl.lunchorderbot.dto.meal.MealGetDto;
import uz.jl.lunchorderbot.dto.meal.MealUpdateDto;
import uz.jl.lunchorderbot.repository.AbstractRepository;
import uz.jl.lunchorderbot.repository.GenericCRUDRepository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class MealRepository extends AbstractRepository implements GenericCRUDRepository<MealCreateDto, MealUpdateDto, MealGetDto, MealDetailDto, Long> {
    private final Type type = new TypeToken<List<MealGetDto>>() {
    }.getType();

    @Override
    public void create(MealCreateDto dto) {
        String url = "http://localhost:80/api/meal/create/";
        HttpEntity<MealCreateDto> request = new HttpEntity<>(dto, headers);

        try {
            template.exchange(url, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MealUpdateDto dto) {
        RestTemplate template1 = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String url = "http://localhost:80/api/meal/update/";
        HttpEntity<MealUpdateDto> request = new HttpEntity<>(dto, headers);

        try {
            template1.exchange(url, HttpMethod.PATCH, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long key) {
        String url = "http://localhost:80/api/meal/delete/" + key + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try {
            template.exchange(url, HttpMethod.DELETE, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MealGetDto get(Long key) {
        String url = "http://localhost:80/api/meal/get/" + key + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MealGetDto dto = gson.fromJson(response.getBody(), MealGetDto.class);
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Meal is null");
        }
        return dto;
    }

    public MealGetDto get(Long id, String chatId) {
        String url = "http://localhost:80/api/meal/get/" + id + "/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MealGetDto dto = gson.fromJson(response.getBody(), MealGetDto.class);
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Meal is null");
        }
        return dto;
    }

    @Override
    public MealDetailDto detail(Long key) {
        return null;
    }

    @Override
    public List<MealGetDto> list() {
        String url = "http://localhost:80/api/meal/list/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<MealGetDto> list = gson.fromJson(response.getBody(), type);
        if (Objects.isNull(list)) {
            throw new RuntimeException("Meal list is null");
        }
        return list;
    }

    public List<MealGetDto> list(String chatId) {
        String url = "http://localhost:80/api/meal/list/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<MealGetDto> list = gson.fromJson(response.getBody(), type);
        if (Objects.isNull(list)) {
            throw new RuntimeException("Meal list is null");
        }
        return list;
    }

    @Override
    public Long totalCount() {
        return null;
    }

    public MealGetDto getByName(String mealName, String chatId) {
        String url = "http://localhost:80/api/meal/get-by-name/" + mealName + "/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MealGetDto dto = gson.fromJson(response.getBody(), MealGetDto.class);
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Meal is null");
        }
        return dto;
    }
}
