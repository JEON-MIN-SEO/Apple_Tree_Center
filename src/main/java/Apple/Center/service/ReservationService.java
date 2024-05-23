package Apple.Center.service;

import Apple.Center.dto.ReservationDTO;
import Apple.Center.eneity.ReservationEntity;
import Apple.Center.fix.MealType;
import Apple.Center.fix.ReservationType;
import Apple.Center.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<ReservationDTO> findReservationsForNextWeek(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(6);
        return reservationRepository.findByReservationDateBetween(startDate, endDate)
                .stream()
                .map(reservation -> new ReservationDTO(
                        reservation.getId(),
                        reservation.getElderlyId(),
                        reservation.getName(),
                        reservation.getFloor(),
                        reservation.getGuardianRelation(),
                        reservation.getType(),
                        reservation.getReservationDate(),
                        reservation.getReservationTime(),
                        reservation.getMeal(),
                        reservation.getRequest(),
                        reservation.getCreatedAt(),
                        reservation.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    public void createVisitReservation(ReservationDTO request) {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setElderlyId(request.getElderlyId());
        reservation.setName(request.getName());
        reservation.setFloor(request.getFloor());
        reservation.setGuardianRelation(request.getGuardianRelation());
        reservation.setType(ReservationType.VISIT);
        reservation.setReservationDate(request.getReservationDate());
        reservation.setReservationTime(request.getReservationTime());
        reservation.setMeal(MealType.INSIDE); // 면회의 경우 식사 옵션이 필요 없으므로 기본값 설정
        reservation.setRequest(request.getRequest());
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        reservationRepository.save(reservation);
    }

    public void createOutingReservation(ReservationDTO request) {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setElderlyId(request.getElderlyId());
        reservation.setName(request.getName());
        reservation.setFloor(request.getFloor());
        reservation.setGuardianRelation(request.getGuardianRelation());
        reservation.setType(ReservationType.OUTING);
        reservation.setReservationDate(request.getReservationDate());
        reservation.setReservationTime(request.getReservationTime());
        reservation.setMeal(request.getMeal());
        reservation.setRequest(request.getRequest());
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        reservationRepository.save(reservation);
    }
    }

}
