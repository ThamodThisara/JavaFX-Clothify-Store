package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTm {
    private Long productId;
    private String name;
    private String category;
    private String size;
    private Integer qty;
    private Double unitPrice;
    private Double totalPrice;
}
