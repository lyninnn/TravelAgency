package org.example.travelagency;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clienteID")
    public int id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "nacionalidad")
    public String nacionalidad;

    @Column(name = "fechaRegistro")
    public LocalDate fechaRegistro;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="viajeId")
    public Viaje viaje;

    public Cliente() {
    }

    public Cliente(String nombre, String nacionalidad, Viaje viaje) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.viaje = viaje;
    }

    public Cliente(int id,String nombre, String nacionalidad, Viaje viaje) {
        this.id=id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaRegistro = LocalDate.now();
        this.viaje = viaje;
    }

    public int getId() {
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", fechaRegistro=" + fechaRegistro
               ;
    }
}
