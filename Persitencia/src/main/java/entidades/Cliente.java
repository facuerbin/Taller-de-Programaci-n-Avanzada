package entidades;

import lombok.*;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Cliente")
@Audited
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Cliente implements Serializable {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private long id;

    @Column(name = "nombre")
    @NonNull private String nombre;

    @Column(name = "apellido")
    @NonNull private String apellido;

    @Column(name = "dni", unique = true)
    @NonNull private  int dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas = new ArrayList<Factura>();

}
