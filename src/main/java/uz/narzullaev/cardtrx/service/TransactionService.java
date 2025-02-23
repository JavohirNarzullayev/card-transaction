package uz.narzullaev.cardtrx.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.narzullaev.cardtrx.constant.TransactionType;
import uz.narzullaev.cardtrx.dto.trx.DepositRqDto;
import uz.narzullaev.cardtrx.dto.trx.WithdrawRqDto;
import uz.narzullaev.cardtrx.entity.Transaction;
import uz.narzullaev.cardtrx.entity.User;
import uz.narzullaev.cardtrx.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;


    @Transactional
    public void withdraw(WithdrawRqDto withdrawRqDto) {
        log.info("withdraw requestId={} userId={}", withdrawRqDto.requestId(), withdrawRqDto.userId());
        User user = userService.getUser(withdrawRqDto.userId());
        if (user.getBalance().compareTo(withdrawRqDto.amount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        user.setBalance(user.getBalance().subtract(withdrawRqDto.amount()));
        userService.saveAndFlush(user);

        var transaction = Transaction.builder()
                .requestId(withdrawRqDto.requestId())
                .type(TransactionType.WITHDRAW.name())
                .userId(withdrawRqDto.userId())
                .amount(withdrawRqDto.amount())
                .remaining(user.getBalance())
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);
    }

    @Transactional
    public void deposit(DepositRqDto depositRqDto) {
        log.info("deposit requestId={} userId={}", depositRqDto.requestId(), depositRqDto.userId());
        User user = userService.getUser(depositRqDto.userId());
        user.setBalance(user.getBalance().add(depositRqDto.amount()));
        userService.saveAndFlush(user);
        var transaction = Transaction.builder()
                .requestId(depositRqDto.requestId())
                .type(TransactionType.DEPOSIT.name())
                .userId(depositRqDto.userId())
                .amount(depositRqDto.amount())
                .remaining(user.getBalance())
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public Page<Transaction> findAllByFilter(
            int page,
            int perPage,
            Long userId,
            LocalDateTime from,
            LocalDateTime to,
            TransactionType type) {

        PageRequest pageRequest = PageRequest.of(page, perPage, Sort.Direction.DESC, "createdAt");
        return transactionRepository.findAll((Specification<Transaction>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();

            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
            }
            if (from != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), from));
            }
            if (to != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), to));
            }
            if (type != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), type.name()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageRequest);
    }
}
