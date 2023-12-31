package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    @OneToMany(mappedBy="speciality",fetch = FetchType.EAGER)
    private Set<SpecialityTechnician> technicians=new HashSet<>();

    //se relaciona con tipo de problema, no con servicios
    @OneToMany(mappedBy="speciality",fetch = FetchType.EAGER)
    private Set<SpecialityTypeProblem> typeProblems=new HashSet<>();

    private boolean active=true;
    public Speciality() {
    }

    public Speciality(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SpecialityTechnician> getTechnicians() {
        return technicians;
    }

    public void setTechnicians(Set<SpecialityTechnician> technicians) {
        this.technicians = technicians;
    }

    public void addTechnician(){

    }

    public Set<SpecialityTypeProblem> getTypeProblems() {
        return typeProblems;
    }

    public void setTypeProblems(Set<SpecialityTypeProblem> typeProblems) {
        this.typeProblems = typeProblems;
    }

    public boolean hasTypeProblem(long typeProblemId){
        Set<Long> typeProblems = getTypeProblemId().stream().map(TypeProblem::getId).collect(Collectors.toSet());
        return typeProblems.contains(typeProblemId);
    }

    public Set<TypeProblem> getTypeProblemId(){
        return typeProblems.stream().map(SpecialityTypeProblem::getTypeProblem).collect(Collectors.toSet());
    }

    public void addTechnician(SpecialityTechnician technician){
        technician.setSpeciality(this);
        this.technicians.add(technician);
    }

    public void addTypeProblem(SpecialityTypeProblem typeProblem){
        typeProblem.setSpeciality(this);
        this.typeProblems.add(typeProblem);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeProblems=" + typeProblems +
                '}';
    }
}
