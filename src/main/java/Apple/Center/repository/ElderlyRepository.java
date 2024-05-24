package Apple.Center.repository;

import Apple.Center.eneity.ElderlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElderlyRepository extends JpaRepository<ElderlyEntity,Long> {
    ElderlyEntity findByName(String name);
}
