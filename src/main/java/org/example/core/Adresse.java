package org.example.core;

public class Adresse {
    private int id;
    private String rue;
    private String ville;

    public Adresse(int id, String rue, String ville) {
        this.id = id;
        this.rue = rue;
        this.ville = ville;
    }

    public int getId() { return id; }
    public String getRue() { return rue; }
    public String getVille() { return ville; }
    public void setRue(String rue) { this.rue = rue; }
    public void setVille(String ville) { this.ville = ville; }

    @Override
    public String toString() {
        return rue + ", " + ville;
    }
}