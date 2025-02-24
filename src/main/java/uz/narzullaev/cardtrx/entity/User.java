package uz.narzullaev.cardtrx.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal balance = BigDecimal.ZERO;
    private LocalDateTime createdAt = LocalDateTime.now();
    @Version
    private Long version;
}
