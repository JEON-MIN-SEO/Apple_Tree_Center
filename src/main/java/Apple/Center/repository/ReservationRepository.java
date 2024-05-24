package Apple.Center.repository;

import Apple.Center.dto.ReservationDTO;
import Apple.Center.eneity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("SELECT r.reservationDate FROM ReservationEntity r WHERE r.reservationDate BETWEEN :startDate AND :endDate")
    List<LocalDate> findUnavailableDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<ReservationEntity> findByReservationDateBetween(LocalDate startDate, LocalDate endDate);

    List<ReservationEntity> findByReservationDate(LocalDate date);
}