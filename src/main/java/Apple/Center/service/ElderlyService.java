package Apple.Center.service;

import Apple.Center.dto.ElderlyDTO;
import Apple.Center.eneity.ElderlyEntity;
import Apple.Center.repository.ElderlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElderlyService {

    @Autowired
    private ElderlyRepository elderlyRepository;

    //모든 어르신 명부 확인
    public List<ElderlyDTO> findAllElderly() {
        return elderlyRepository.findAll().stream()
                .map(elderly -> new ElderlyDTO(
                        elderly.getId(),
                        elderly.getName(),
                        elderly.getFloor()))
                .collect(Collectors.toList());
    }

    public ElderlyDTO findElderlyByName(String name) {
        ElderlyEntity elderly = elderlyRepository.findByName(name);
        if (elderly != null) {
            return new ElderlyDTO(elderly.getId(), elderly.getName(), elderly.getFloor());
        }
        return null;
    }

    //조회
    public ElderlyDTO findElderlyById(Long id) {
        ElderlyEntity elderly = elderlyRepository.findById(id).orElseThrow(() -> new RuntimeException("어르신 정보를 찾을 수 없습니다."));
        return new ElderlyDTO(elderly.getId(), elderly.getName(), elderly.getFloor());
    }

    //생성
    public void createElderly(ElderlyDTO elderlyDTO) {
        ElderlyEntity elderlyEntity = new ElderlyEntity(null, elderlyDTO.getName(), elderlyDTO.getFloor());
        elderlyRepository.save(elderlyEntity);
    }

    //수정
    public void updateElderly(ElderlyDTO elderlyDTO) {
        ElderlyEntity elderlyEntity = new ElderlyEntity(elderlyDTO.getId(), elderlyDTO.getName(), elderlyDTO.getFloor());
        elderlyRepository.save(elderlyEntity);
    }

    //삭제
    public void deleteElderly(Long id) {
        elderlyRepository.deleteById(id);
    }
}

