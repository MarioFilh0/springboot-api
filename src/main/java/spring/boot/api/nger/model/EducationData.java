package spring.boot.api.nger.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EducationData {
    private String profissionalizanteFormal;
    private String enem;
    private String encceja;
    private String outrasAtividadesEducacionais;
}
