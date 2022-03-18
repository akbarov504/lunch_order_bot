package uz.jl.lunchorderbot.dto.department;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentUpdateDto extends GenericDto {
    private String name;

    @SerializedName(value = "user_id")
    private Long userId;
}
