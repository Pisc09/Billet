package org.example.services;

import org.example.core.*;
import org.example.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ConsoleService {
    private int nextAdresseId = 1;
    private int nextLieuId = 1;
    private int nextClientId = 1;
    private int nextEvenementId = 1;
    private int nextBilletId = 1;

    private final List<Adresse> adresses = new ArrayList<>();
    private final List<Lieu> lieux = new ArrayList<>();
    private final List<Client> clients = new ArrayList<>();
    private final List<Evenement> evenements = new ArrayList<>();
    private final List<Billet> billets = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    // Constructeur corrigé
    public ConsoleService() {
        // Pas besoin de throws NotFoundException
    }

    public void start() {
        // ... reste inchangé ...
    }

    // Méthodes CRUD...

    // Méthodes utilitaires corrigées
    private Adresse trouverAdresse(int id) throws NotFoundException {
        for (Adresse a : adresses) {
            if (a.getId() == id) return a;
        }
        throw new NotFoundException("Adresse non trouvée: " + id);
    }

    private Lieu trouverLieu(int id) throws NotFoundException {
        for (Lieu l : lieux) {
            if (l.getId() == id) return l;
        }
        throw new NotFoundException("Lieu non trouvé: " + id);
    }

    private Client trouverClient(int id) throws NotFoundException {
        for (Client c : clients) {
            if (c.getId() == id) return c;
        }
        throw new NotFoundException("Client non trouvé: " + id);
    }

    private Evenement trouverEvenement(int id) throws NotFoundException {
        for (Evenement e : evenements) {
            if (e.getId() == id) return e;
        }
        throw new NotFoundException("Événement non trouvé: " + id);
    }

    private Billet trouverBillet(int id) throws NotFoundException {
        for (Billet b : billets) {
            if (b.getId() == id) return b;
        }
        throw new NotFoundException("Billet non trouvé: " + id);
    }
}