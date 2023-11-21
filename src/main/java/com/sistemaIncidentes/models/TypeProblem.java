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

    private String name;

    @OneToMany(mappedBy="typeProblem",fetch = FetchType.EAGER)
    private Set<Problem> problems=new HashSet<>();

    @OneToMany(mappedBy="typeProblem",fetch = FetchType.EAGER)
    private Set<SpecialityTypeProblem> specialities=new HashSet<>();


    public TypeProblem() {
    }

    public TypeProblem(double maxTime, String name) {
        this.maxTime = maxTime;
        this.name = name;
    }

    public long getId() {
        return id;
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

    public void addTypeProblem(SpecialityTypeProblem typeProblem){
        typeProblem.setTypeProblem(this);
        this.specialities.add(typeProblem);
    }

    public void addProblem(Problem problem){
        problem.setTypeProblem(this);
        this.problems.add(problem);
    }
}