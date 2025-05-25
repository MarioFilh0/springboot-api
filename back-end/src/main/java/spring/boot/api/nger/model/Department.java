package spring.boot.api.nger.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonBackReference
    @OneToOne(mappedBy = "dep")
    private User user;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Info> infos = new ArrayList<>();

    public void addInfo(Info info) {
        if (info != null) {
            if (this.infos == null) {
                this.infos = new ArrayList<>();
            }
            this.infos.add(info);
            info.setDepartment(this);
        }
    }

    public void removeInfo(Info info) {
        if (info != null && this.infos != null) {
            this.infos.remove(info);
            info.setDepartment(null); // Remove a associação
        }
    }
}
