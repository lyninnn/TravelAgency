package org.example.travelagency;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "viajes")
public class Viaje{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viajeId")
    private int id;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "pais")
    private String pais;

    @Column(name = "precio")
    private int precio;



    @OneToMany(mappedBy = "viaje",fetch = FetchType.LAZY)
    private ArrayList<Cliente> clientes;


    
}
viajeId INT AUTO_INCREMENT PRIMARY KEY,
ciudad VARCHAR(100) NOT NULL,
pais VARCHAR(100) NOT NULL,
precio INT NOT NULL,
clienteId INT,
FOREIGN KEY (clienteId) REFERENCES Clientes(clienteId)
