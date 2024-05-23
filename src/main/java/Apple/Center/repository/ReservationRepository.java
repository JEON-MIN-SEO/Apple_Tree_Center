package Apple.Center.repository;

import Apple.Center.dto.ReservationDTO;
import Apple.Center.eneity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository //시작일 ~ 끝나는일
public interface ReservationRepository extends JpaRepository<ReservationDTO, Long> {
    List<ReservationEntity> findByReservationDateBetween(LocalDate startDate, LocalDate endDate);
}