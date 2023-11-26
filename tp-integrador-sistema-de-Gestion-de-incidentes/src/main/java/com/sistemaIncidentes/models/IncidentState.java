package com.sistemaIncidentes.models;

public interface IncidentState {

    public double getTime();
    public void close(double timeInHours);
    public void setIncident(Incident incident);
    public Incident getIncident();
}
