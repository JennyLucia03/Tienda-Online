
package Modelo;


public class Pago {
    private int id;
    private String direccion;
    private long numTarj;
    private String fechaexp;
    private int cvc;
    private String tipCuenta;
    private long numCuenta;
    private String banco;
    
    public Pago(){
    }

    public Pago(int id, String direccion, long numTarj, String fechaexp, int cvc, String tipCuenta, long numCuenta, String banco) {
        this.id = id;
        this.direccion = direccion;
        this.numTarj = numTarj;
        this.fechaexp = fechaexp;
        this.cvc = cvc;
        this.tipCuenta = tipCuenta;
        this.numCuenta = numCuenta;
        this.banco = banco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getNumTarj() {
        return numTarj;
    }

    public void setNumTarj(long numTarj) {
        this.numTarj = numTarj;
    }

    public String getFechaexp() {
        return fechaexp;
    }

    public void setFechaexp(String fechaexp) {
        this.fechaexp = fechaexp;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getTipCuenta() {
        return tipCuenta;
    }

    public void setTipCuenta(String tipCuenta) {
        this.tipCuenta = tipCuenta;
    }

    public long getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(long numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    
}
