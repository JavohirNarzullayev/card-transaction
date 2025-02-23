package uz.narzullaev.cardtrx.dto.user;

import java.math.BigDecimal;

public record UserRqDto(
        String name, BigDecimal balance
) {
}
