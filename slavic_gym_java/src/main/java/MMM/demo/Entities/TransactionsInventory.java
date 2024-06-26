
package MMM.demo.Entities;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionsInventory {
    private Integer id_transaction;

    private Integer id_item;

    private Integer id_member;

    private OffsetDateTime order_time;

    private Integer quantity;

}
