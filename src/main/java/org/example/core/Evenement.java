package org.example.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Evenement {
    private int id;
    private String nom;
    private Lieu lieu;
    private LocalDate date;
    private LocalTime heure;
    private int nombrePlaces;
    private List<Billet> billets = new ArrayList<>();

    public Evenement(int id, String nom, Lieu lieu, LocalDate date, LocalTime heure, int nombrePlaces) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.heure = heure;
        this.nombrePlaces = nombrePlaces;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public Lieu getLieu() { return lieu; }
    public LocalDate getDate() { return date; }
    public LocalTime getHeure() { return heure; }
    public int getNombrePlaces() { return nombrePlaces; }
    public List<Billet> getBillets() { return billets; }
    public int getPlacesDisponibles() {
        return nombrePlaces - billets.size();
    }

    // Setters
    public void setNom(String nom) { this.nom = nom; }
    public void setLieu(Lieu lieu) { this.lieu = lieu; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setHeure(LocalTime heure) { this.heure = heure; }
    public void setNombrePlaces(int nombrePlaces) { this.nombrePlaces = nombrePlaces; }

    public void ajouterBillet(Billet billet) {
        billets.add(billet);
    }

    public void supprimerBillet(Billet billet) {
        billets.remove(billet);
    }

    @Override
    public String toString() {
        return nom + " - " + date + " à " + heure + " - " + lieu;
    }
}