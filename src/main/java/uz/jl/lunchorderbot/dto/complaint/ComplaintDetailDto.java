package uz.jl.lunchorderbot.dto.complaint;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDetailDto extends GenericDto {
    private String message;

    @SerializedName(value = "user_id")
    private Long user_id;
}
