package spring.boot.api.nger.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class WorkData {
    private String marcenaria;
    private String construçãoCivil;
    private String culinaria;
    private String padaria;
    private String corteDeCostura;
    private String esteticista;
    private String fábricaDeFraldas;
    private String fábricaDeAbsorventes;
    private String manutençãoDeArCondicionado;
    private String limpezaEConservação;
    private String eletricista;
    private String campanhas;
}
