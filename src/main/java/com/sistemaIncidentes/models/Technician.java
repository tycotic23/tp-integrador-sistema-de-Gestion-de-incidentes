package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private String email;

    @OneToMany(mappedBy="technician",fetch = FetchType.EAGER)
    private Set<Incident> incidents=new HashSet<>();

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="speciality_id")
    private Speciality speciality;

    public Technician() {
    }

    public Technician(String name, String email, Speciality speciality) {
        this.name = name;
        this.email = email;
        this.speciality = speciality;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents) {
        this.incidents = incidents;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void addIncident(Incident incident){
        incident.setTechnician(this);
        this.incidents.add(incident);
    }
}
