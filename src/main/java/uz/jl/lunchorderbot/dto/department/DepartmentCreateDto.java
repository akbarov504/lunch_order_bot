package uz.jl.lunchorderbot.dto.department;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.BaseDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreateDto implements BaseDto {
    private String name;

    @SerializedName(value = "user_id")
    private Long userId;

    @SerializedName(value = "created_by")
    private Long createdBy;
}
