package uz.jl.lunchorderbot.dto.token;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.BaseDto;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenCreateDto implements BaseDto {
    @SerializedName(value = "user_id")
    private Long userId;

    private String token;

    private String type;

    private LocalDate duration;

    @SerializedName(value = "created_by")
    private Long createdBy;
}
