
package Modelo;

public class DetalleCompra {
    int id;
    int idproducto;
    int idcompra;
    int cantidad;
    double precioCompra;
    Compra compra;
    Producto producto;
    
    public DetalleCompra(){
    }

    public DetalleCompra(int id, int idproducto, int idcompra, int cantidad, double precioCompra, Compra compra, Producto producto) {
        this.id = id;
        this.idproducto = idproducto;
        this.idcompra = idcompra;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.compra = compra;
        this.producto = producto;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

}
