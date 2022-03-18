package uz.jl.lunchorderbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LunchOrderBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(LunchOrderBotApplication.class, args);
        TelegramBotService service = new TelegramBotService();
        service.run();
    }
}
