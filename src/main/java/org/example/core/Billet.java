package org.example.core;

public class Billet {
    private int id;
    private String numeroPlace;
    private Client client;
    private Evenement evenement;
    private TypePlace typePlace;

    public Billet(int id, String numeroPlace, Client client, Evenement evenement, TypePlace typePlace) {
        this.id = id;
        this.numeroPlace = numeroPlace;
        this.client = client;
        this.evenement = evenement;
        this.typePlace = typePlace;
    }

    // Getters
    public int getId() { return id; }
    public String getNumeroPlace() { return numeroPlace; }
    public Client getClient() { return client; }
    public Evenement getEvenement() { return evenement; }
    public TypePlace getTypePlace() { return typePlace; }

    // Setters
    public void setNumeroPlace(String numeroPlace) { this.numeroPlace = numeroPlace; }
    public void setTypePlace(TypePlace typePlace) { this.typePlace = typePlace; }

    @Override
    public String toString() {
        return "Billet #" + id + " - Place " + numeroPlace + " (" + typePlace + ") - " + evenement.getNom();
    }
}