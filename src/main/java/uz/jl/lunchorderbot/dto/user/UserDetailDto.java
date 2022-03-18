package uz.jl.lunchorderbot.dto.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto extends GenericDto {
    private String username;

    private UUID code;

    private String password;

    private String fullName;

    private String phone;

    private String language;

    private String position;

    private Integer chatId;

    @SerializedName(value = "department_id")
    private Long departmentId;

    private List<Long> orders;
}
