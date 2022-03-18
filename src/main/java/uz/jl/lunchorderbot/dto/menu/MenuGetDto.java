package uz.jl.lunchorderbot.dto.menu;

import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MenuGetDto extends GenericDto {
    private List<Long> meals;
}
