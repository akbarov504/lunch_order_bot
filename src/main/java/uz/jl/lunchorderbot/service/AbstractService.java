package uz.jl.lunchorderbot.service;

import uz.jl.lunchorderbot.repository.BaseRepository;

public abstract class AbstractService<R extends BaseRepository> implements BaseService {
    protected final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }
}
