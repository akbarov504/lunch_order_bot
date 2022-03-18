package uz.jl.lunchorderbot.handlers;

import uz.jl.lunchorderbot.service.BaseService;

public abstract class AbstractHandler<S extends BaseService> implements BaseHandler {
    protected final S service;

    protected AbstractHandler(S service) {
        this.service = service;
    }
}
