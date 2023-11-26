package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    @OneToMany(mappedBy="speciality",fetch = FetchType.EAGER)
    private Set<Technician> technicians=new HashSet<>();

    //se relaciona con tipo de problema, no con servicios
    @OneToMany(mappedBy="speciality",fetch = FetchType.EAGER)
    private Set<SpecialityTypeProblem> typeProblems=new HashSet<>();

    public Speciality() {
    }

    public Speciality(String name) {
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

    public Set<Technician> getTechnicians() {
        return technicians;
    }

    public void setTechnicians(Set<Technician> technicians) {
        this.technicians = technicians;
    }

    public Set<SpecialityTypeProblem> getTypeProblems() {
        return typeProblems;
    }

    public void setTypeProblems(Set<SpecialityTypeProblem> typeProblems) {
        this.typeProblems = typeProblems;
    }

    public void addTechnician(Technician technician){
        technician.setSpeciality(this);
        this.technicians.add(technician);
    }

    public void addTypeProblem(SpecialityTypeProblem typeProblem){
        typeProblem.setSpeciality(this);
        this.typeProblems.add(typeProblem);
    }
}
