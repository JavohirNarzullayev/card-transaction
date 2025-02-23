package uz.narzullaev.cardtrx.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String type; // DEPOSIT, WITHDRAW
    private LocalDateTime createdAt = LocalDateTime.now();

    @Version
    private Long version;

    // Getters and Setters
}
