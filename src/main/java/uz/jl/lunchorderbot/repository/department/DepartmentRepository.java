package uz.jl.lunchorderbot.repository.department;

import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.jl.lunchorderbot.dto.department.DepartmentCreateDto;
import uz.jl.lunchorderbot.dto.department.DepartmentDetailDto;
import uz.jl.lunchorderbot.dto.department.DepartmentGetDto;
import uz.jl.lunchorderbot.dto.department.DepartmentUpdateDto;
import uz.jl.lunchorderbot.repository.AbstractRepository;
import uz.jl.lunchorderbot.repository.GenericCRUDRepository;

import javax.ws.rs.NotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class DepartmentRepository extends AbstractRepository implements GenericCRUDRepository<DepartmentCreateDto, DepartmentUpdateDto, DepartmentGetDto, DepartmentDetailDto, Long> {
    private final Type type = new TypeToken<List<DepartmentGetDto>>() {
    }.getType();

    @Override
    public void create(DepartmentCreateDto dto) {
        String url = "http://localhost:80/api/department/create/";
        HttpEntity<DepartmentCreateDto> request = new HttpEntity<>(dto, headers);

        try {
            template.exchange(url, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DepartmentUpdateDto dto) {
        RestTemplate template1 = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String url = "http://localhost:80/api/department/update/";
        HttpEntity<DepartmentUpdateDto> request = new HttpEntity<>(dto, headers);

        try {
            template1.exchange(url, HttpMethod.PATCH, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long key) {
        String url = "http://localhost:80/api/department/delete/" + key + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try {
            template.exchange(url, HttpMethod.DELETE, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DepartmentGetDto get(Long key) {
        String url = "http://localhost:80/api/department/get/" + key + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        DepartmentGetDto dto = gson.fromJson(response.getBody(), DepartmentGetDto.class);
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Department is null");
        }
        return dto;
    }

    @Override
    public DepartmentDetailDto detail(Long key) {
        return null;
    }

    @Override
    public List<DepartmentGetDto> list() {
        String url = "http://localhost:80/api/department/list/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<DepartmentGetDto> list = gson.fromJson(response.getBody(), type);
        if (Objects.isNull(list)) {
            throw new RuntimeException("Department list is null");
        }
        return list;
    }

    @Override
    public Long totalCount() {
        return null;
    }

    public DepartmentGetDto getByName(String name) {
        String url = "http://localhost:80/api/department/get-by-name/" + name + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        DepartmentGetDto dto = gson.fromJson(response.getBody(), DepartmentGetDto.class);
        if (Objects.isNull(dto)) {
            throw new NotFoundException("Department not found");
        }
        return dto;
    }
}
