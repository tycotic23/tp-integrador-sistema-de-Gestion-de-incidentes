package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class SpecialityTechnician {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="speciality_id")
    private Speciality speciality;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="technician_id")
    private Technician technician;

    public SpecialityTechnician() {
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

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }
}
