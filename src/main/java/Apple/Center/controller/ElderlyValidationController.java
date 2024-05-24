package Apple.Center.controller;

import Apple.Center.dto.ElderlyDTO;
import Apple.Center.service.ElderlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class ElderlyValidationController {

    @Autowired
    private ElderlyService elderlyService;

    //어르신 이름 검증
    @GetMapping("/elderly")
    public ResponseEntity<?> validateElderly(@RequestParam("name") String name) {
        ElderlyDTO elderlyDTO = elderlyService.findElderlyByName(name);
        if (elderlyDTO != null) {
            return ResponseEntity.ok(elderlyDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("어르신 정보를 찾을 수 없습니다.");
        }
    }
}
