package Apple.Center.service;

import Apple.Center.dto.ElderlyDTO;
import Apple.Center.eneity.ElderlyEntity;
import Apple.Center.repository.ElderlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElderlyService {

    @Autowired
    private ElderlyRepository elderlyRepository;

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

