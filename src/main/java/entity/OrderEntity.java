package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity (name = "ordertable")
public class OrderEntity {
    @Id
    private String orderId;
    private LocalDate date;
    private String customerName;
    private String customerEmail;
}