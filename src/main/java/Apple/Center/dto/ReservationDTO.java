package Apple.Center.dto;

import Apple.Center.fix.Meal;
import Apple.Center.fix.ReservationType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class ReservationDTO {
    private Long id;
    private Long elderly_id;
    private String guardian_relation;
    private ReservationType type;
    private LocalDate reservation_date;
    private LocalTime reservation_time;
    private Meal meal;
    private String request;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
