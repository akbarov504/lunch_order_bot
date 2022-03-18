package uz.jl.lunchorderbot.dto.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto extends GenericDto {
    private String username;

    private String fullName;

    private String phone;

    private String position;

    @SerializedName(value = "department_id")
    private Long departmentId;
}
