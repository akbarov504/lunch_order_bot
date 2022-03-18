package uz.jl.lunchorderbot.dto.order;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDto extends GenericDto {
    private LocalDateTime date;

    private Boolean receive;

    @SerializedName(value = "to_take")
    private Boolean toTake;

    @SerializedName(value = "user_id")
    private Long userId;

    @SerializedName(value = "meal_id")
    private Long mealId;
}
