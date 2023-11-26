package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class SpecialityTypeProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="speciality_id")
    private Speciality speciality;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="typeProblem_id")
    private TypeProblem typeProblem;

    public SpecialityTypeProblem() {
    }

    public long getId() {
        return id;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public TypeProblem getTypeProblem() {
        return typeProblem;
    }

    public void setTypeProblem(TypeProblem typeProblem) {
        this.typeProblem = typeProblem;
    }
}
