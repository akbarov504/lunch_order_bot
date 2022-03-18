package uz.jl.lunchorderbot.repository.order;

import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import uz.jl.lunchorderbot.configuration.map.SessionMap;
import uz.jl.lunchorderbot.dto.order.OrderCreateDto;
import uz.jl.lunchorderbot.dto.order.OrderDetailDto;
import uz.jl.lunchorderbot.dto.order.OrderGetDto;
import uz.jl.lunchorderbot.dto.order.OrderUpdateDto;
import uz.jl.lunchorderbot.repository.AbstractRepository;
import uz.jl.lunchorderbot.repository.GenericCRUDRepository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class OrderRepository extends AbstractRepository implements GenericCRUDRepository<OrderCreateDto, OrderUpdateDto, OrderGetDto, OrderDetailDto, Long> {
    private final Type type = new TypeToken<List<OrderGetDto>>() {
    }.getType();

    @Override
    public void create(OrderCreateDto dto) {

    }

    @Override
    public void update(OrderUpdateDto dto) {

    }

    @Override
    public void delete(Long key) {

    }

    public void delete(Long key, String chatId) {
        String url = "http://localhost:80/api/order/delete/" + key + "/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try {
            template.exchange(url, HttpMethod.DELETE, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderGetDto get(Long key) {
        return null;
    }

    @Override
    public OrderDetailDto detail(Long key) {
        return null;
    }

    @Override
    public List<OrderGetDto> list() {
        return null;
    }

    @Override
    public Long totalCount() {
        return null;
    }

    public List<OrderGetDto> history(Long userId, String chatId) {
        String url = "http://localhost:80/api/order/history/" + userId + "/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = template.exchange(url, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<OrderGetDto> list = gson.fromJson(response.getBody(), type);
        if (Objects.isNull(list)) {
            throw new RuntimeException("order list is null");
        }
        return list;
    }

    public void create(OrderCreateDto dto, String chatId) {
        String url = "http://localhost:80/api/order/create/";
        headers.add("Authorization", "Bearer " + SessionMap.getToken(chatId));
        HttpEntity<OrderCreateDto> request = new HttpEntity<>(dto, headers);

        try {
            template.exchange(url, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
