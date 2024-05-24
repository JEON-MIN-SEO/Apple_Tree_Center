package Apple.Center.controller;

import Apple.Center.dto.ReservationDTO;
import Apple.Center.fix.ReservationType;
import Apple.Center.service.ElderlyService;
import Apple.Center.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

// 예약 관리
@Controller
@RequestMapping("/admin")
public class AdminReservationController {


    @Autowired
    private ElderlyService elderlyService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDTO>> getReservationsForNextWeek(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<ReservationDTO> reservations = reservationService.findReservationsForNextWeek(date);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Void> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        if (reservationDTO.getType() == ReservationType.VISIT) {
            reservationService.createVisitReservation(reservationDTO);
        } else if (reservationDTO.getType() == ReservationType.OUTING) {
            reservationService.createOutingReservation(reservationDTO);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/reservations")
    public ResponseEntity<Void> updateReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        reservationService.updateReservation(reservationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
