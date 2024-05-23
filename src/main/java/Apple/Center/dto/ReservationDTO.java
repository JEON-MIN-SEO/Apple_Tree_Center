package Apple.Center.dto;

import Apple.Center.fix.MealType;
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
    private Long elderlyId; // ElderlyDTO.id
    private String name; // ElderlyDTO.name
    private int floor; // ElderlyDTO.floor
    private String guardianRelation;
    private ReservationType type;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private MealType meal;
    private String request;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
