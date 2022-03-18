package uz.jl.lunchorderbot.enums.position;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PositionEnum {
    ADMIN("Admin"),
    MENTOR("Mentor"),
    EMPLOYEE("Employee"),
    MANAGER("Manager");

    private final String value;
}