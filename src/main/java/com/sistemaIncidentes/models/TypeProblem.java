package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class TypeProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double maxTime;
    private double estimatedTime;

    private String name;

    @OneToMany(mappedBy="typeProblem",fetch = FetchType.EAGER)
    private Set<Problem> problems=new HashSet<>();

    @OneToMany(mappedBy="typeProblem",fetch = FetchType.EAGER)
    private Set<SpecialityTypeProblem> specialities=new HashSet<>();

    private boolean active=true;
    public TypeProblem() {
    }

    public TypeProblem(double maxTime, String name,double estimatedTime) {
        this.maxTime = maxTime;
        this.name = name;
        this.estimatedTime=estimatedTime;
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

    public double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(double maxTime) {
        this.maxTime = maxTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    public Set<SpecialityTypeProblem> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<SpecialityTypeProblem> specialities) {
        this.specialities = specialities;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void addSpeciality(SpecialityTypeProblem speciality){
        speciality.setTypeProblem(this);
        this.specialities.add(speciality);
    }

    public void addProblem(Problem problem){
        problem.setTypeProblem(this);
        this.problems.add(problem);
    }

    @Override
    public String toString() {
        return "TypeProblem{" +
                "id=" + id +
                ", maxTime=" + maxTime +
                ", estimatedTime=" + estimatedTime +
                ", name='" + name + '\'' +
                '}';
    }
}