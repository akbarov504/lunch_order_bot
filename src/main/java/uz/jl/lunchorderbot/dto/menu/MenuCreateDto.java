package uz.jl.lunchorderbot.dto.menu;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.BaseDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MenuCreateDto implements BaseDto {
    private String date;

    private List<Long> meals;

    @SerializedName(value = "created_by")
    private Long createdBy;
}
