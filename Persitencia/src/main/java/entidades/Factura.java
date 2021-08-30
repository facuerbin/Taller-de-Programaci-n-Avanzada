package entidades;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Factura")
@Audited
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Factura implements Serializable {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private long id;

    @Column(name = "fecha")
    @NonNull private String fecha;

    @Column(name = "numero")
    @NonNull private int numero;

    @Column(name = "total")
    @NonNull private int total;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    // Relación Unidireccional
    //@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    //private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();

    // Relación Bidireccional
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();

}
