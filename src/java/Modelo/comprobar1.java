
package Modelo;

public class comprobar1 {
    public static void main(String[] args){
        conexion1 c = new conexion1();
        if(c.conectar()!= null){
            System.out.println("conexion es correcta");
        }else{
            System.out.println("conexion erronea");
        }
    }
    
}
