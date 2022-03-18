package uz.jl.lunchorderbot.repository;

import uz.jl.lunchorderbot.dto.BaseDto;
import uz.jl.lunchorderbot.dto.GenericDto;

import java.io.Serializable;
import java.util.List;

public interface GenericCRUDRepository<CD extends BaseDto, UD extends GenericDto, GD extends GenericDto, DD extends GenericDto, K extends Serializable> extends BaseRepository {
    void create(CD dto);

    void update(UD dto);

    void delete(K key);

    GD get(K key);

    DD detail(K key);

    List<GD> list();

    Long totalCount();
}
