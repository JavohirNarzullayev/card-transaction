package uz.narzullaev.cardtrx.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    private UUID requestId;
    private Long userId;
    private BigDecimal amount;
    private BigDecimal remaining;
    private String type; // DEPOSIT, WITHDRAW
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    // Getters and Setters
}
