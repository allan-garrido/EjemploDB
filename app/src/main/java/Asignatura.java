/**
 * Created by Allan Garrido on 24/07/2017.
 */

public class Asignatura {
    private int codigo, cantEstudiantes;
    private String nombre;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantEstudiantes() {
        return cantEstudiantes;
    }

    public void setCantEstudiantes(int cantEstudiantes) {
        this.cantEstudiantes = cantEstudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura(int codigo, int cantEstudiantes, String nombre) {

        this.codigo = codigo;
        this.cantEstudiantes = cantEstudiantes;
        this.nombre = nombre;
    }

    public Asignatura() {

    }
}


