package org.example.core;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private Adresse adresse;
    private int age;
    private String telephone;
    private List<Billet> billets = new ArrayList<>();

    public Client(int id, String nom, String prenom, Adresse adresse, int age, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.age = age;
        this.telephone = telephone;
    }

    // Getters et setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public Adresse getAdresse() { return adresse; }
    public int getAge() { return age; }
    public String getTelephone() { return telephone; }
    public List<Billet> getBillets() { return billets; }

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
    public void setAge(int age) { this.age = age; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public void ajouterBillet(Billet billet) {
        billets.add(billet);
    }

    public void supprimerBillet(Billet billet) {
        billets.remove(billet);
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " (" + age + " ans) - " + adresse;
    }
}