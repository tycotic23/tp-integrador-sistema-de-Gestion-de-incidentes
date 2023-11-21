package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String businessName;
    private String CUIT;
    private String email;

    @OneToMany(mappedBy="client",fetch = FetchType.EAGER)
    private Set<ClientService> hiredServices=new HashSet<>();

    @OneToMany(mappedBy="client",fetch = FetchType.EAGER)
    private Set<Incident> incidents=new HashSet<>();

    public Client() {
    }

    public Client(String businessName, String CUIT, String email) {
        this.businessName = businessName;
        this.CUIT = CUIT;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ClientService> getHiredServices() {
        return hiredServices;
    }

    public void setHiredServices(Set<ClientService> hiredServices) {
        this.hiredServices = hiredServices;
    }

    public Set<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents) {
        this.incidents = incidents;
    }

    public void addService(ClientService clientService){
        clientService.setClient(this);
        this.hiredServices.add(clientService);
    }

    public void addIncident(Incident incident){
        incident.setClient(this);
        this.incidents.add(incident);
    }
}
