package uz.narzullaev.cardtrx.dto.trx;


import java.math.BigDecimal;
import java.util.UUID;

public record WithdrawRqDto(
        UUID requestId,
        BigDecimal amount,
        long userId
){}
