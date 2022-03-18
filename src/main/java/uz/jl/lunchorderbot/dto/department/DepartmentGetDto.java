package uz.jl.lunchorderbot.dto.department;

import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentGetDto extends GenericDto {
    private String name;

    private UUID code;

    private Long userId;
}
