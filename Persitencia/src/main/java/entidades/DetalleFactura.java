package entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DetalleFactura")
public class DetalleFactura implements Serializable {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "sub_total")
    private int subTotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_factura")
    private Factura factura;

    // Constructor
    public DetalleFactura() {
    }

    public DetalleFactura(int cantidad, int subTotal) {
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public DetalleFactura(int cantidad, int subTotal, Articulo articulo) {
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.articulo = articulo;
    }

    public DetalleFactura(int cantidad, int subTotal, Articulo articulo, Factura factura) {
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.articulo = articulo;
        this.factura = factura;
    }

    // Getters && Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
