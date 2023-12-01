package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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

    private boolean available=true;

    @OneToMany(mappedBy="technician",fetch = FetchType.EAGER)
    private Set<Incident> incidents=new HashSet<>();

    @OneToMany(mappedBy="technician",fetch = FetchType.EAGER)
    private Set<SpecialityTechnician> specialities=new HashSet<>();

    private boolean active=true;

    public Technician() {
    }

    public Technician(String name, String email) {
        this.name = name;
        this.email = email;
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
        if(incidents.isEmpty()){
            return 0;
        }
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

    public Set<Speciality> getSpecialitiesList() {
        //obtengo las especialidades a partir del objeto intermedio SpecialityTechnician
        return specialities.stream().map(SpecialityTechnician::getSpeciality).collect(Collectors.toSet());
    }

    public boolean hasSpeciality(String specialityName){
        Set<String> specialities = getSpecialitiesList().stream().map(Speciality::getName).collect(Collectors.toSet());
        return specialities.contains(specialityName);
    }

    public boolean canResolveTypeProblem(TypeProblem typeProblem){
        return specialities.stream().anyMatch(s->s.getSpeciality().hasTypeProblem(typeProblem.getId()));
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



    public void closeIncident(){
        available=true;
        Incident incident=getActiveIncident();
        if(incident!=null){
            getActiveIncident().setSolved(true);
        }
        System.out.println("No hay incidentes en curso");
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Incident getActiveIncident(){
        return incidents.stream().filter(i->!i.isSolved()).findFirst().orElse(null);
    }

    public double getAverage(){
        if(getIncidents().isEmpty()){
            return 0;
        }
        List<Incident> incidentList = getIncidents().stream().filter(Incident::isSolved).collect(Collectors.toList());
        double totalTime=incidentList.stream().map(Incident::getTime).reduce(Double::sum).orElse(0.0);
        if(totalTime==0){
            return 0;
        }
        int incidents=getIncidentsSolvedNumber();
        if(incidents==0){
            return 0;
        }
        return totalTime/incidents;
    }

    @Override
    public String toString() {
        return "Technician{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", available='" + available + '\'' +
                ", incidents=" + incidents +
                ", specialities=" + getSpecialitiesList() +
                '}';
    }

    public void report(){
        System.out.println("id=" + id +
                ", nombre='" + name + '\'' +
                ", disponible='" + available + '\'');
        System.out.println("Incidentes: ");
        incidents.forEach(i->{
            System.out.println("id="+i.getId());
            System.out.println("inicio="+i.getDate());
            System.out.println("fin="+i.getEstimatedDate());
            System.out.println("estado="+i.isSolved());
            System.out.println();

        });
    }



}
