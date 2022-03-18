package uz.jl.lunchorderbot.dto.complaint;

import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintGetDto extends GenericDto {
    private String message;

    private Long userId;
}
