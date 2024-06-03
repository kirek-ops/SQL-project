
package MMM.demo.Entities;

import java.time.Duration;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseLogsTime {
    private Integer id_log;

    private Integer id_member;

    private Integer id_goal;

    private OffsetDateTime log_date;

    private Duration time_done;

}
