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
import java.time.LocalTime;
import java.util.ArrayList;
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

    public void updateReservation(ReservationDTO request) {
        ReservationEntity reservation = reservationRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setElderlyId(request.getElderlyId());
        reservation.setName(request.getName());
        reservation.setFloor(request.getFloor());
        reservation.setGuardianRelation(request.getGuardianRelation());
        reservation.setType(request.getType());
        reservation.setReservationDate(request.getReservationDate());
        reservation.setReservationTime(request.getReservationTime());
        reservation.setMeal(request.getMeal());
        reservation.setRequest(request.getRequest());
        reservation.setUpdatedAt(LocalDateTime.now());

        reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }


    public List<LocalDate> getAvailableDates(LocalDate today) {
        LocalDate startDate = today.plusDays(1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        List<LocalDate> allDates = startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());

        List<LocalDate> unavailableDates = reservationRepository.findUnavailableDates(startDate, endDate);

        return allDates.stream()
                .filter(date -> !unavailableDates.contains(date))
                .collect(Collectors.toList());
    }

    public List<LocalTime> getAvailableTimes(LocalDate date, ReservationType type) {
        List<ReservationEntity> reservations = reservationRepository.findByReservationDate(date);

        if (type == ReservationType.VISIT) {
            List<LocalTime> visitTimes = List.of(LocalTime.of(10, 30), LocalTime.of(11, 0), LocalTime.of(14, 0), LocalTime.of(15, 0), LocalTime.of(16, 0));
            return visitTimes.stream()
                    .filter(time -> reservations.stream().filter(r -> r.getType() == ReservationType.VISIT && r.getReservationTime().equals(time)).count() < 1)
                    .collect(Collectors.toList());
        } else {
            List<LocalTime> outingTimes = new ArrayList<>();
            for (LocalTime time = LocalTime.of(10, 0); !time.isAfter(LocalTime.of(16, 0)); time = time.plusMinutes(30)) {
                outingTimes.add(time);
            }
            return outingTimes.stream()
                    .filter(time -> reservations.stream().filter(r -> r.getType() == ReservationType.OUTING && r.getReservationTime().equals(time)).count() < 2)
                    .collect(Collectors.toList());
        }
    }
}
