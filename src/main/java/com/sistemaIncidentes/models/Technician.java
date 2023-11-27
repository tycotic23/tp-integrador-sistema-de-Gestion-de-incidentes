package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy="technician",fetch = FetchType.EAGER)
    private Set<SpecialityTechnician> specialities=new HashSet<>();

    public Technician() {
    }

    public Technician(String name, String email) {
        this.name = name;
        this.email = email;
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

    public int getIncidentsSolvedNumber(){
        return this.incidents.stream().filter(Incident::isSolved).collect(Collectors.toSet()).size();
    }

    public int getIncidentsSolvedNumberFromLastNDays(int n){
        LocalDate dateFilter=LocalDate.now().minusDays(n);
        return this.incidents.stream().filter(Incident::isSolved).filter(i->i.getDate().isAfter(dateFilter)).collect(Collectors.toSet()).size();
    }


    public void setIncidents(Set<Incident> incidents) {
        this.incidents = incidents;
    }

    public Set<SpecialityTechnician> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<SpecialityTechnician> specialities) {
        this.specialities = specialities;
    }

    public void addSpeciality(SpecialityTechnician speciality){
        speciality.setTechnician(this);
        this.specialities.add(speciality);
    }

    public void addIncident(Incident incident){
        incident.setTechnician(this);
        this.incidents.add(incident);
    }

    @Override
    public String toString() {
        return "Technician{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", incidents=" + incidents +
                '}';
    }

    public String toStringTechIncidents(){
        return "Technician{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", incidents=" + incidents +
                '}';
    }



}
