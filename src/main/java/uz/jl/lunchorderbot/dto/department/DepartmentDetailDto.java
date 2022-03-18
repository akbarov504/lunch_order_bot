package uz.jl.lunchorderbot.dto.department;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDetailDto extends GenericDto {
    private String name;

    private UUID code;

    @SerializedName(value = "user_id")
    private Long userId;
}
