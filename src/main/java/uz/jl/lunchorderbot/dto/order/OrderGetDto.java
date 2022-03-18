package uz.jl.lunchorderbot.dto.order;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderGetDto extends GenericDto {
    private Boolean receive;

    private Boolean toTake;

    private Long userId;

    private Long mealId;
}
