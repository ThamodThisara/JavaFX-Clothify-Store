package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail {
    private String orderId;
    private Long productId;
    private Integer qty;

    public OrderDetail(String orderId,Long productId,Integer qty){
        this.orderId = orderId;
        this.productId = productId;
        this.qty = qty;
    }
}
