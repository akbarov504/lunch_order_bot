package uz.jl.lunchorderbot.repository;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractRepository {
    protected final Gson gson = new Gson();
    protected final RestTemplate template = new RestTemplate();
    protected final HttpHeaders headers = new HttpHeaders();

    {
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }
}
