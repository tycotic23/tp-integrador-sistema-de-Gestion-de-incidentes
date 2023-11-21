package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    @OneToMany(mappedBy="service",fetch = FetchType.EAGER)
    private Set<ClientService> clients=new HashSet<>();

    @OneToMany(mappedBy="service",fetch = FetchType.EAGER)
    private Set<Incident> incidents=new HashSet<>();

    public Set<Incident> getIncidents(){
        /*puede obtener todos los incidentes a partir de sus clientes*/
        return new HashSet<>();
    }

    public void addClient(ClientService clientService){
        clientService.setService(this);
        this.clients.add(clientService);
    }

    public void addIncident(Incident incident){
        incident.setService(this);
        this.incidents.add(incident);
    }
}
