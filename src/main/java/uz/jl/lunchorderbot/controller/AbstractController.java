package uz.jl.lunchorderbot.controller;

import uz.jl.lunchorderbot.service.BaseService;

public abstract class AbstractController<S extends BaseService> implements BaseController {
    protected final S service;

    protected AbstractController(S service) {
        this.service = service;
    }
}
