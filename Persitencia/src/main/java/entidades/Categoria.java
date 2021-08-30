package entidades;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categoria")
@Audited
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Categoria implements Serializable {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private long id;

    @Column(name = "denominacion")
    @NonNull private String denominacion;

    @ManyToMany(mappedBy = "categorias")
    @NonNull private List<Articulo> articulos = new ArrayList<Articulo>();

}
