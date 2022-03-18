package uz.jl.lunchorderbot.service.order;

import org.jvnet.hk2.annotations.Service;
import uz.jl.lunchorderbot.dto.order.OrderCreateDto;
import uz.jl.lunchorderbot.dto.order.OrderDetailDto;
import uz.jl.lunchorderbot.dto.order.OrderGetDto;
import uz.jl.lunchorderbot.dto.order.OrderUpdateDto;
import uz.jl.lunchorderbot.repository.order.OrderRepository;
import uz.jl.lunchorderbot.service.AbstractService;
import uz.jl.lunchorderbot.service.GenericCRUDService;

import java.util.List;

@Service
public class OrderService extends AbstractService<OrderRepository> implements GenericCRUDService<OrderCreateDto, OrderUpdateDto, OrderGetDto, OrderDetailDto, Long> {
    public OrderService(OrderRepository repository) {
        super(repository);
    }

    @Override
    public void create(OrderCreateDto dto) {

    }

    public void create(OrderCreateDto dto, String chatId) {
        repository.create(dto, chatId);
    }

    @Override
    public void update(OrderUpdateDto dto) {

    }

    @Override
    public void delete(Long key) {
        repository.delete(key);
    }

    public void delete(Long key, String chatId) {
        repository.delete(key, chatId);
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
        return repository.history(userId, chatId);
    }
}
