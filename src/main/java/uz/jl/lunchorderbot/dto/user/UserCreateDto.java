package uz.jl.lunchorderbot.dto.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import uz.jl.lunchorderbot.dto.BaseDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto implements BaseDto {
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String language;
    private String position;
    @SerializedName(value = "chat_id")
    private Integer chatId;
    @SerializedName(value = "created_by")
    private Long createdBy = -1L;
}
