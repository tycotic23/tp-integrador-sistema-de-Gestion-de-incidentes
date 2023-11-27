package com.sistemaIncidentes.models;

public class IncidentPending implements IncidentState {
    private Incident incident;

    public IncidentPending() {
    }

    @Override
    public double getTime() {
        //recorre la lista de problemas y suma el tiempo de todos ellos
        return this.incident.getProblems().stream().map(Problem::getEstimatedTime).reduce(0.0, Double::sum);
    }

    @Override
    public void close(double timeInHours) {
        //incident.setIncidentState(new IncidentSolved(timeInHours));
    }

    @Override
    public void setIncident(Incident incident) {
        this.incident=incident;
    }

    @Override
    public Incident getIncident() {
        return this.incident;
    }
}
