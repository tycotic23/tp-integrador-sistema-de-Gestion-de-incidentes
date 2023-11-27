package com.sistemaIncidentes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    //private IncidentState incidentState=new IncidentPending();
    private boolean solved=true;

    private LocalDate date;

    @OneToMany(mappedBy="incident",fetch = FetchType.EAGER)
    private Set<Problem> problems=new HashSet<>();

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="service_id")
    private Service service;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="technician_id")
    private Technician technician;

    public Incident() {
        date=LocalDate.now();
    }



    public long getId() {
        return id;
    }

   public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    /*public IncidentState getIncidentState() {
        return incidentState;
    }

    public void setIncidentState(IncidentState incidentState) {
        this.incidentState = incidentState;
        this.incidentState.setIncident(this);
    }*/

    public void addProblem(Problem problem) {
        problem.setIncident(this);
        this.problems.add(problem);
    }

    /*public double getTime(){
        return this.incidentState.getTime();
    }

    public void closeIncident(double timeInHours){
        this.incidentState.close(timeInHours);
    }*/

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", problems=" + problems +
                '}';
    }
}
