
package MMM.demo.Entities;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientMembership {
    private Integer id_member;

    private Integer id_membership;

    private LocalDate start_date;

}
