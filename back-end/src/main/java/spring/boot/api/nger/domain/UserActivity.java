package spring.boot.api.nger.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import spring.boot.api.nger.model.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;

    private String department;

    private Integer profissionalizante = 0;
    private Integer formal = 0;
    private Integer enem = 0;
    private Integer encceja = 0;
    private Integer otherEducation = 0;


    private Integer marcenaria = 0;
    private Integer oficinaCostura = 0;
    private Integer fabricaBlocos = 0;
    private Integer artesanato = 0;
    private Integer oficinaMecanica = 0;
    private Integer construcaoCivil = 0;
    private Integer culinaria = 0;
    private Integer padaria = 0;
    private Integer corteCostura = 0;
    private Integer esteticista = 0;
    private Integer fabricaFraldas = 0;
    private Integer fabricaAbsorventes = 0;
    private Integer manutencaoArCondicionado = 0;
    private Integer eletricista = 0;
    private Integer limpezaConservacao = 0;
    private Integer campanhas = 0;

    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}