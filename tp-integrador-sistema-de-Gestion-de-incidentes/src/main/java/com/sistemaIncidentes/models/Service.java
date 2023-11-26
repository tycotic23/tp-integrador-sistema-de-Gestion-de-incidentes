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

    public Service() {
    }

    public Service(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   public Set<ClientService> getClients() {
        return clients;
    }

    public void setClients(Set<ClientService> clients) {
        this.clients = clients;
    }

    public Set<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents) {
        this.incidents = incidents;
    }

    public void addClient(ClientService clientService){
        clientService.setService(this);
        this.clients.add(clientService);
    }

    public void addIncident(Incident incident){
        incident.setService(this);
        this.incidents.add(incident);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
