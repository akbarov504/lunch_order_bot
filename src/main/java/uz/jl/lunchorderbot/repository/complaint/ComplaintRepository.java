package uz.jl.lunchorderbot.repository.complaint;

import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.configuration.map.SessionMap;
import uz.jl.lunchorderbot.dto.complaint.ComplaintCreateDto;
import uz.jl.lunchorderbot.dto.complaint.ComplaintDetailDto;
import uz.jl.lunchorderbot.dto.complaint.ComplaintGetDto;
import uz.jl.lunchorderbot.dto.complaint.ComplaintUpdateDto;
import uz.jl.lunchorderbot.repository.AbstractRepository;
import uz.jl.lunchorderbot.repository.GenericCRUDRepository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class ComplaintRepository extends AbstractRepository implements GenericCRUDRepository<ComplaintCreateDto, ComplaintUpdateDto, ComplaintGetDto, ComplaintDetailDto, Long> {
    private final Type type = new TypeToken<List<ComplaintGetDto>>() {
    }.getType();

    @Override
    public void create(ComplaintCreateDto dto) {

    }

    @Override
    public void update(ComplaintUpdateDto dto) {

    }

    @Override
    public void delete(Long key) {
        String url = "http://localhost:80/api/complaint/delete/" + key + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try {
            template.exchange(url, HttpMethod.DELETE, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ComplaintGetDto get(Long key) {
        String url = "http://localhost:80/api/complaint/get/" + key + "/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ComplaintGetDto dto = gson.fromJson(response.getBody(), ComplaintGetDto.class);
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Complaint is null");
        }
        return dto;
    }

    @Override
    public ComplaintDetailDto detail(Long key) {
        return null;
    }

    @Override
    public List<ComplaintGetDto> list() {
        String url = "http://localhost:80/api/complaint/list/";
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<ComplaintGetDto> list = gson.fromJson(response.getBody(), type);
        if (Objects.isNull(list)) {
            throw new RuntimeException("Complaint list is null");
        }
        return list;
    }

    @Override
    public Long totalCount() {
        return null;
    }

    public List<ComplaintGetDto> list(Long id, String chatId) {
        String url = "http://localhost:80/api/complaint/list/" + id + "/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<ComplaintGetDto> list = gson.fromJson(response.getBody(), type);
        if (Objects.isNull(list)) {
            throw new RuntimeException("Complaint list is null");
        }
        return list;
    }

    public void delete(Long id, String chatId) {
        String url = "http://localhost:80/api/complaint/delete/" + id + "/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try {
            template.exchange(url, HttpMethod.DELETE, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void create(ComplaintCreateDto dto, String chatId) {
        String url = "http://localhost:80/api/complaint/create/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<ComplaintCreateDto> request = new HttpEntity<>(dto, headers);

        try {
            template.exchange(url, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
