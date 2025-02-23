package uz.narzullaev.cardtrx.dto.trx;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Builder;
import uz.narzullaev.cardtrx.constant.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record TransactionResDto (
        UUID requestId,
        long userId,
        String type,
        BigDecimal amount,
        BigDecimal remaining,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        LocalDateTime createdAt
){

}
