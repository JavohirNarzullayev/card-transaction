package uz.narzullaev.cardtrx.resource.rest.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import uz.narzullaev.cardtrx.constant.TransactionType;
import uz.narzullaev.cardtrx.dto.BaseResponse;
import uz.narzullaev.cardtrx.dto.SuccessDataIterable;
import uz.narzullaev.cardtrx.dto.trx.DepositRqDto;
import uz.narzullaev.cardtrx.dto.trx.TransactionResDto;
import uz.narzullaev.cardtrx.dto.trx.WithdrawRqDto;
import uz.narzullaev.cardtrx.entity.Transaction;
import uz.narzullaev.cardtrx.mapper.TrxMapper;
import uz.narzullaev.cardtrx.service.TransactionService;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/transaction")
@Tag(name = "Методы для трансакции")
@RequiredArgsConstructor
public class TransactionResource {

    private final TransactionService transactionService;

    @PostMapping("/withdraw")
    @Operation(summary = "Снять из баланса")
    public BaseResponse<Void> withdraw(
            @RequestBody WithdrawRqDto dto
    ) {
        transactionService.withdraw(dto);
        return BaseResponse.successResponse(null);
    }

    @PostMapping("/deposit")
    @Operation(summary = "Пополнят баланс")
    public BaseResponse<Void> deposit(
            @RequestBody DepositRqDto dto
    ) {
        transactionService.deposit(dto);
        return BaseResponse.successResponse(null);
    }

    @GetMapping("/filter")
    @Operation(summary = "Фильтр данных")
    public SuccessDataIterable<TransactionResDto> filter(
            @RequestParam int page,
            @RequestParam int perPage,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false)
            @Parameter(description = "Start date/time in the format yyyy-MM-dd HH:mm:ss", example = "2024-12-12 12:12:12")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime from,
            @RequestParam(required = false)
            @Parameter(description = "End date/time in the format yyyy-MM-dd HH:mm:ss", example = "2024-12-12 12:12:12")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to,
            @RequestParam(required = false) TransactionType type) {

        Page<Transaction> allByFilter = transactionService.findAllByFilter(
                page, perPage, userId, from, to, type);
        return new SuccessDataIterable<>(allByFilter.map(TrxMapper::toResponse));
    }
}
