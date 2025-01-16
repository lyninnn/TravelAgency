package org.example.travelagency;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "viajes")
public class Viaje{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viajeId")
    public int id;

    @Column(name = "ciudad")
    public String ciudad;

    @Column(name = "pais")
    public String pais;

    @Column(name = "precio")
    public int precio;



    @OneToMany(mappedBy = "viaje",fetch = FetchType.EAGER)
    public List<Cliente> clientes;


    public Viaje() {
    }

    public Viaje(String ciudad, String pais, int precio) {
        this.ciudad = ciudad;
        this.pais = pais;
        this.precio = precio;
        this.clientes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", precio=" + precio
                ;
    }
}
//viajeId INT AUTO_INCREMENT PRIMARY KEY,
//ciudad VARCHAR(100) NOT NULL,
//pais VARCHAR(100) NOT NULL,
//precio INT NOT NULL,
//clienteId INT,
//FOREIGN KEY (clienteId) REFERENCES Clientes(clienteId)
