package com.sistemaIncidentes.models;

public class IncidentSolved implements IncidentState {

    private Incident incident;
    private double timeResolution;

    public IncidentSolved() {
    }

    public IncidentSolved(double timeResolution) {
        this.timeResolution = timeResolution;
    }

    @Override
    public double getTime() {
        return timeResolution;
    }

    @Override
    public void close(double timeInHours) {
        System.out.println("Already solved");
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
