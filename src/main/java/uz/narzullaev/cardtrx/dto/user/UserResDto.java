package uz.narzullaev.cardtrx.dto.user;


import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UserResDto(
        Long id,
        String name,
        BigDecimal balance
) {
}
