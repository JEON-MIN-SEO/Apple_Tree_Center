package Apple.Center.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class ElderlyDTO {
    private Long id;

    @NotNull(message = "이름을 입력해야 합니다.")
    private String name;

    @NotNull(message = "층을 입력해야 합니다.")
    private int floor;
}
