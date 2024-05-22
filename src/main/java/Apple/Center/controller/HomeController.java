package Apple.Center.controller;

import Apple.Center.dto.ReservationDTO;
import Apple.Center.fix.ReservationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/reservation/meeting")
    public String meetingReservation(Model model) {
        ReservationDTO reservationDTO = new ReservationDTO(ReservationType.VISIT); // 면회 예약용 DTO 생성
        model.addAttribute("reservationDTO", reservationDTO);
        return "meeting_reservation"; // 면회 예약 페이지를 반환합니다.
    }

    @GetMapping("/reservation/outdoor")
    public String outdoorReservation(Model model) {
        ReservationDTO reservationDTO = new ReservationDTO(ReservationType.OUTING); // 외출 예약용 DTO 생성
        model.addAttribute("reservationDTO", reservationDTO);
        return "outdoor_reservation"; // 외출 예약 페이지를 반환합니다.
    }
}
