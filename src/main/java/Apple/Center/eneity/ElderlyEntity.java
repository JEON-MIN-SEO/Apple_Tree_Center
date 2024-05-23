package Apple.Center.eneity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
@Table(name = "elderly_table")
// @NoArgsConstructor 모든 필드를 받아야 함으로 기본 생성자는 필요하지 않다고 판단
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class ElderlyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int floor;
}