package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private boolean isComplex;
    private double estimatedTime;
    private String description;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="incident_id")
    private Incident incident;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="typeProblem_id")
    private TypeProblem typeProblem;

    public Problem() {
    }

    public Problem(boolean isComplex, double estimatedTime, String description, TypeProblem typeProblem) {
        this.isComplex = isComplex;
        this.estimatedTime = estimatedTime;
        this.description = description;
        this.typeProblem = typeProblem;
    }

    public long getId() {
        return id;
    }

    public boolean isComplex() {
        return isComplex;
    }

    public void setComplex(boolean complex) {
        isComplex = complex;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public TypeProblem getTypeProblem() {
        return typeProblem;
    }

    public void setTypeProblem(TypeProblem typeProblem) {
        this.typeProblem = typeProblem;
    }
}

