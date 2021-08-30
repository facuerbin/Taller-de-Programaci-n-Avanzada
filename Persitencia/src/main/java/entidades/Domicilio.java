package entidades;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Domicilio")
@Audited
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Domicilio implements Serializable {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private long id;

    @Column(name = "calle")
    @NonNull private String nombreCalle;

    @Column(name = "numero")
    @NonNull private int numero;

    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;

}
