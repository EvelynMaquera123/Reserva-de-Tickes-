package ne.subgrupo_catorce.proyecto_final.entidades;

import java.io.Serializable;

public class Eventos implements Serializable {

    private Integer id;
    private String nombre;
    private String titulo;
    private Double latitud;
    private Double longitud;
    private String fecha;
    private String descripcion;
    public Eventos(){

    }
    public  Eventos(Integer id, String nombre, String titulo, Double latitud, Double longitud, String fecha, String descripcion){
        setId(id);
        setNombre(nombre);
        setTitulo(titulo);
        setLatitud(latitud);
        setLongitud(longitud);
        setFecha(fecha);
        setDescripcion(descripcion);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
