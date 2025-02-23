package uz.narzullaev.cardtrx.mapper;

import lombok.experimental.UtilityClass;
import uz.narzullaev.cardtrx.dto.trx.TransactionResDto;
import uz.narzullaev.cardtrx.dto.user.UserResDto;
import uz.narzullaev.cardtrx.dto.user.UserRqDto;
import uz.narzullaev.cardtrx.entity.Transaction;
import uz.narzullaev.cardtrx.entity.User;

@UtilityClass
public class TrxMapper {


    public TransactionResDto toResponse(Transaction transaction) {
        return TransactionResDto.builder()
                .requestId(transaction.getRequestId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .remaining(transaction.getRemaining())
                .userId(transaction.getUserId())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
