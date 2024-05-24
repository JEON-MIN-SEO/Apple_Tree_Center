package Apple.Center.controller;

import Apple.Center.dto.ElderlyDTO;
import Apple.Center.service.ElderlyService;
import Apple.Center.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/elderly")
public class AdminElderlyController {

    @Autowired
    private ElderlyService elderlyService;

    @Autowired
    private ReservationService reservationService;

    //모든 어르신 이름 조회
    @GetMapping("/elderly")
    public ResponseEntity<List<ElderlyDTO>> getAllElderly() {
        List<ElderlyDTO> elderlyList = elderlyService.findAllElderly();
        return ResponseEntity.ok(elderlyList);
    }

    //어드민 어르신 추가
    @PostMapping("/elderly")
    public ResponseEntity<Void> createElderly(@Valid @RequestBody ElderlyDTO elderlyDTO) {
        elderlyService.createElderly(elderlyDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //어드민 어르신 수정
    @PutMapping("/elderly")
    public ResponseEntity<Void> updateElderly(@Valid @RequestBody ElderlyDTO elderlyDTO) {
        elderlyService.updateElderly(elderlyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //어드민 어르신 삭제
    @DeleteMapping("/elderly/{id}")
    public ResponseEntity<Void> deleteElderly(@PathVariable Long id) {
        elderlyService.deleteElderly(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
