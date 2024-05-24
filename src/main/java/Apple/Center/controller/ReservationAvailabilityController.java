package Apple.Center.controller;

import Apple.Center.fix.ReservationType;
import Apple.Center.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/availability")
public class ReservationAvailabilityController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/dates")
    public ResponseEntity<List<LocalDate>> getAvailableDates(@RequestParam("today") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate today) {
        List<LocalDate> availableDates = reservationService.getAvailableDates(today);
        return ResponseEntity.ok(availableDates);
    }

    @GetMapping("/times")
    public ResponseEntity<List<LocalTime>> getAvailableTimes(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                             @RequestParam("type") ReservationType type) {
        List<LocalTime> availableTimes = reservationService.getAvailableTimes(date, type);
        return ResponseEntity.ok(availableTimes);
    }
}
