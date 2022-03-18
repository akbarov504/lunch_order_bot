package uz.jl.lunchorderbot.dto.order;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.BaseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto implements BaseDto {
    private LocalDateTime date;

    @SerializedName(value = "user_id")
    private Long userId;

    @SerializedName(value = "meal_id")
    private Long mealId;

    @SerializedName(value = "created_by")
    private Long createdBy;
}
