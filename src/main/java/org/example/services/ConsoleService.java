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

    public ConsoleService() {
        // Constructeur vide
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== GESTION BILLETTERIE ===");
            System.out.println("1. Gérer les adresses");
            System.out.println("2. Gérer les lieux");
            System.out.println("3. Gérer les clients");
            System.out.println("4. Gérer les événements");
            System.out.println("5. Gérer les billets");
            System.out.println("6. Quitter");
            System.out.print("Choix: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: gererAdresses(); break;
                    case 2: gererLieux(); break;
                    case 3: gererClients(); break;
                    case 4: gererEvenements(); break;
                    case 5: gererBillets(); break;
                    case 6: running = false; break;
                    default: System.out.println("Choix invalide!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide!");
            } catch (NotFoundException e) {
                System.out.println("Erreur: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Format de date/heure invalide!");
            } catch (Exception e) {
                System.out.println("Erreur inattendue: " + e.getMessage());
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    // Méthodes CRUD pour les adresses
    private void gererAdresses() throws NotFoundException {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- GESTION ADRESSES ---");
            System.out.println("1. Ajouter une adresse");
            System.out.println("2. Lister toutes les adresses");
            System.out.println("3. Modifier une adresse");
            System.out.println("4. Supprimer une adresse");
            System.out.println("5. Retour");
            System.out.print("Choix: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: ajouterAdresse(); break;
                case 2: listerAdresses(); break;
                case 3: modifierAdresse(); break;
                case 4: supprimerAdresse(); break;
                case 5: back = true; break;
                default: System.out.println("Choix invalide!");
            }
        }
    }

    private void ajouterAdresse() {
        System.out.print("Rue: ");
        String rue = scanner.nextLine();
        System.out.print("Ville: ");
        String ville = scanner.nextLine();

        Adresse adresse = new Adresse(nextAdresseId++, rue, ville);
        adresses.add(adresse);
        System.out.println("Adresse ajoutée avec ID: " + adresse.getId());
    }

    private void listerAdresses() {
        if (adresses.isEmpty()) {
            System.out.println("Aucune adresse enregistrée.");
            return;
        }

        for (Adresse a : adresses) {
            System.out.println(a.getId() + " - " + a);
        }
    }

    private void modifierAdresse() throws NotFoundException {
        listerAdresses();
        if (adresses.isEmpty()) return;

        System.out.print("ID de l'adresse à modifier: ");
        int id = Integer.parseInt(scanner.nextLine());
        Adresse adresse = trouverAdresse(id);

        System.out.print("Nouvelle rue (" + adresse.getRue() + "): ");
        String rue = scanner.nextLine();
        if (!rue.isEmpty()) adresse.setRue(rue);

        System.out.print("Nouvelle ville (" + adresse.getVille() + "): ");
        String ville = scanner.nextLine();
        if (!ville.isEmpty()) adresse.setVille(ville);

        System.out.println("Adresse modifiée!");
    }

    private void supprimerAdresse() throws NotFoundException {
        listerAdresses();
        if (adresses.isEmpty()) return;

        System.out.print("ID de l'adresse à supprimer: ");
        int id = Integer.parseInt(scanner.nextLine());
        Adresse adresse = trouverAdresse(id);

        // Vérifier si l'adresse est utilisée
        for (Client c : clients) {
            if (c.getAdresse() == adresse) {
                System.out.println("Impossible de supprimer: adresse utilisée par un client!");
                return;
            }
        }

        adresses.remove(adresse);
        System.out.println("Adresse supprimée!");
    }

    // Méthodes CRUD pour les lieux
    private void gererLieux() throws NotFoundException {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- GESTION LIEUX ---");
            System.out.println("1. Ajouter un lieu");
            System.out.println("2. Lister tous les lieux");
            System.out.println("3. Modifier un lieu");
            System.out.println("4. Supprimer un lieu");
            System.out.println("5. Retour");
            System.out.print("Choix: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: ajouterLieu(); break;
                case 2: listerLieux(); break;
                case 3: modifierLieu(); break;
                case 4: supprimerLieu(); break;
                case 5: back = true; break;
                default: System.out.println("Choix invalide!");
            }
        }
    }

    private void ajouterLieu() {
        System.out.print("Rue: ");
        String rue = scanner.nextLine();
        System.out.print("Ville: ");
        String ville = scanner.nextLine();
        System.out.print("Capacité: ");
        int capacite = Integer.parseInt(scanner.nextLine());

        Lieu lieu = new Lieu(nextLieuId++, rue, ville, capacite);
        lieux.add(lieu);
        System.out.println("Lieu ajouté avec ID: " + lieu.getId());
    }

    private void listerLieux() {
        if (lieux.isEmpty()) {
            System.out.println("Aucun lieu enregistré.");
            return;
        }

        for (Lieu l : lieux) {
            System.out.println(l.getId() + " - " + l);
        }
    }

    private void modifierLieu() throws NotFoundException {
        listerLieux();
        if (lieux.isEmpty()) return;

        System.out.print("ID du lieu à modifier: ");
        int id = Integer.parseInt(scanner.nextLine());
        Lieu lieu = trouverLieu(id);

        System.out.print("Nouvelle rue (" + lieu.getRue() + "): ");
        String rue = scanner.nextLine();
        if (!rue.isEmpty()) lieu.setRue(rue);

        System.out.print("Nouvelle ville (" + lieu.getVille() + "): ");
        String ville = scanner.nextLine();
        if (!ville.isEmpty()) lieu.setVille(ville);

        System.out.print("Nouvelle capacité (" + lieu.getCapacite() + "): ");
        String capaciteStr = scanner.nextLine();
        if (!capaciteStr.isEmpty()) {
            lieu.setCapacite(Integer.parseInt(capaciteStr));
        }

        System.out.println("Lieu modifié!");
    }

    private void supprimerLieu() throws NotFoundException {
        listerLieux();
        if (lieux.isEmpty()) return;

        System.out.print("ID du lieu à supprimer: ");
        int id = Integer.parseInt(scanner.nextLine());
        Lieu lieu = trouverLieu(id);

        // Vérifier si le lieu est utilisé
        for (Evenement e : evenements) {
            if (e.getLieu() == lieu) {
                System.out.println("Impossible de supprimer: lieu utilisé dans un événement!");
                return;
            }
        }

        lieux.remove(lieu);
        System.out.println("Lieu supprimé!");
    }

    // Méthodes CRUD pour les clients
    private void gererClients() throws NotFoundException {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- GESTION CLIENTS ---");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Lister tous les clients");
            System.out.println("3. Modifier un client");
            System.out.println("4. Supprimer un client");
            System.out.println("5. Retour");
            System.out.print("Choix: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: ajouterClient(); break;
                case 2: listerClients(); break;
                case 3: modifierClient(); break;
                case 4: supprimerClient(); break;
                case 5: back = true; break;
                default: System.out.println("Choix invalide!");
            }
        }
    }

    private void ajouterClient() throws NotFoundException {
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();

        // Sélection d'une adresse
        listerAdresses();
        if (adresses.isEmpty()) {
            System.out.println("Veuillez d'abord créer une adresse!");
            return;
        }

        System.out.print("ID de l'adresse: ");
        int adresseId = Integer.parseInt(scanner.nextLine());
        Adresse adresse = trouverAdresse(adresseId);

        System.out.print("Âge: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Téléphone: ");
        String telephone = scanner.nextLine();

        Client client = new Client(nextClientId++, nom, prenom, adresse, age, telephone);
        clients.add(client);
        System.out.println("Client ajouté avec ID: " + client.getId());
    }

    private void listerClients() {
        if (clients.isEmpty()) {
            System.out.println("Aucun client enregistré.");
            return;
        }

        for (Client c : clients) {
            System.out.println(c.getId() + " - " + c);
        }
    }

    private void modifierClient() throws NotFoundException {
        listerClients();
        if (clients.isEmpty()) return;

        System.out.print("ID du client à modifier: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client client = trouverClient(id);

        System.out.print("Nouveau nom (" + client.getNom() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) client.setNom(nom);

        System.out.print("Nouveau prénom (" + client.getPrenom() + "): ");
        String prenom = scanner.nextLine();
        if (!prenom.isEmpty()) client.setPrenom(prenom);

        System.out.print("Nouvel âge (" + client.getAge() + "): ");
        String ageStr = scanner.nextLine();
        if (!ageStr.isEmpty()) client.setAge(Integer.parseInt(ageStr));

        System.out.print("Nouveau téléphone (" + client.getTelephone() + "): ");
        String tel = scanner.nextLine();
        if (!tel.isEmpty()) client.setTelephone(tel);

        // Modification adresse
        listerAdresses();
        System.out.print("Nouvel ID d'adresse (enter pour ne pas changer): ");
        String adresseIdStr = scanner.nextLine();
        if (!adresseIdStr.isEmpty()) {
            Adresse adresse = trouverAdresse(Integer.parseInt(adresseIdStr));
            client.setAdresse(adresse);
        }

        System.out.println("Client modifié!");
    }

    private void supprimerClient() throws NotFoundException {
        listerClients();
        if (clients.isEmpty()) return;

        System.out.print("ID du client à supprimer: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client client = trouverClient(id);

        // Supprimer tous les billets associés
        for (Billet b : new ArrayList<>(client.getBillets())) {
            supprimerBillet(b);
        }

        clients.remove(client);
        System.out.println("Client supprimé!");
    }

    // Méthodes CRUD pour les événements
    private void gererEvenements() throws NotFoundException {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- GESTION ÉVÉNEMENTS ---");
            System.out.println("1. Ajouter un événement");
            System.out.println("2. Lister tous les événements");
            System.out.println("3. Modifier un événement");
            System.out.println("4. Supprimer un événement");
            System.out.println("5. Lister les billets d'un événement");
            System.out.println("6. Retour");
            System.out.print("Choix: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: ajouterEvenement(); break;
                case 2: listerEvenements(); break;
                case 3: modifierEvenement(); break;
                case 4: supprimerEvenement(); break;
                case 5: listerBilletsEvenement(); break;
                case 6: back = true; break;
                default: System.out.println("Choix invalide!");
            }
        }
    }

    private void ajouterEvenement() throws NotFoundException {
        System.out.print("Nom: ");
        String nom = scanner.nextLine();

        // Sélection du lieu
        listerLieux();
        if (lieux.isEmpty()) {
            System.out.println("Veuillez d'abord créer un lieu!");
            return;
        }

        System.out.print("ID du lieu: ");
        int lieuId = Integer.parseInt(scanner.nextLine());
        Lieu lieu = trouverLieu(lieuId);

        System.out.print("Date (AAAA-MM-JJ): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Heure (HH:MM): ");
        LocalTime heure = LocalTime.parse(scanner.nextLine());

        System.out.print("Nombre de places: ");
        int nombrePlaces = Integer.parseInt(scanner.nextLine());

        // Vérifier que le nombre de places <= capacité du lieu
        if (nombrePlaces > lieu.getCapacite()) {
            System.out.println("Attention: capacité du lieu insuffisante!");
            System.out.println("Capacité max: " + lieu.getCapacite());
            nombrePlaces = lieu.getCapacite();
        }

        Evenement event = new Evenement(nextEvenementId++, nom, lieu, date, heure, nombrePlaces);
        evenements.add(event);
        System.out.println("Événement ajouté avec ID: " + event.getId());
    }

    private void listerEvenements() {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement enregistré.");
            return;
        }

        for (Evenement e : evenements) {
            System.out.println(e.getId() + " - " + e + " - Places: " +
                    e.getPlacesDisponibles() + "/" + e.getNombrePlaces());
        }
    }

    private void modifierEvenement() throws NotFoundException {
        listerEvenements();
        if (evenements.isEmpty()) return;

        System.out.print("ID de l'événement à modifier: ");
        int id = Integer.parseInt(scanner.nextLine());
        Evenement event = trouverEvenement(id);

        System.out.print("Nouveau nom (" + event.getNom() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) event.setNom(nom);

        // Modification lieu
        listerLieux();
        System.out.print("Nouvel ID de lieu (enter pour ne pas changer): ");
        String lieuIdStr = scanner.nextLine();
        if (!lieuIdStr.isEmpty()) {
            Lieu lieu = trouverLieu(Integer.parseInt(lieuIdStr));
            event.setLieu(lieu);
        }

        System.out.print("Nouvelle date (" + event.getDate() + "): ");
        String dateStr = scanner.nextLine();
        if (!dateStr.isEmpty()) event.setDate(LocalDate.parse(dateStr));

        System.out.print("Nouvelle heure (" + event.getHeure() + "): ");
        String heureStr = scanner.nextLine();
        if (!heureStr.isEmpty()) event.setHeure(LocalTime.parse(heureStr));

        System.out.print("Nouveau nombre de places (" + event.getNombrePlaces() + "): ");
        String placesStr = scanner.nextLine();
        if (!placesStr.isEmpty()) {
            int places = Integer.parseInt(placesStr);
            if (places > event.getLieu().getCapacite()) {
                System.out.println("Capacité du lieu insuffisante! Gardé: " + event.getLieu().getCapacite());
                places = event.getLieu().getCapacite();
            }
            event.setNombrePlaces(places);
        }

        System.out.println("Événement modifié!");
    }

    private void supprimerEvenement() throws NotFoundException {
        listerEvenements();
        if (evenements.isEmpty()) return;

        System.out.print("ID de l'événement à supprimer: ");
        int id = Integer.parseInt(scanner.nextLine());
        Evenement event = trouverEvenement(id);

        // Supprimer tous les billets associés
        for (Billet b : new ArrayList<>(event.getBillets())) {
            supprimerBillet(b);
        }

        evenements.remove(event);
        System.out.println("Événement supprimé!");
    }

    private void listerBilletsEvenement() throws NotFoundException {
        listerEvenements();
        if (evenements.isEmpty()) return;

        System.out.print("ID de l'événement: ");
        int id = Integer.parseInt(scanner.nextLine());
        Evenement event = trouverEvenement(id);

        if (event.getBillets().isEmpty()) {
            System.out.println("Aucun billet pour cet événement.");
            return;
        }

        System.out.println("Billets pour " + event.getNom() + ":");
        for (Billet b : event.getBillets()) {
            System.out.println("  " + b);
        }
    }

    // Méthodes CRUD pour les billets
    private void gererBillets() throws NotFoundException {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- GESTION BILLETS ---");
            System.out.println("1. Réserver un billet");
            System.out.println("2. Lister tous les billets");
            System.out.println("3. Modifier un billet");
            System.out.println("4. Annuler un billet");
            System.out.println("5. Retour");
            System.out.print("Choix: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: reserverBillet(); break;
                case 2: listerBillets(); break;
                case 3: modifierBillet(); break;
                case 4: annulerBillet(); break;
                case 5: back = true; break;
                default: System.out.println("Choix invalide!");
            }
        }
    }

    private void reserverBillet() throws NotFoundException {
        // Sélection de l'événement
        listerEvenements();
        if (evenements.isEmpty()) return;

        System.out.print("ID de l'événement: ");
        int eventId = Integer.parseInt(scanner.nextLine());
        Evenement event = trouverEvenement(eventId);

        // Vérifier les places disponibles
        if (event.getPlacesDisponibles() <= 0) {
            System.out.println("Plus de places disponibles pour cet événement!");
            return;
        }

        // Sélection du client
        listerClients();
        if (clients.isEmpty()) {
            System.out.println("Veuillez d'abord créer un client!");
            return;
        }

        System.out.print("ID du client: ");
        int clientId = Integer.parseInt(scanner.nextLine());
        Client client = trouverClient(clientId);

        System.out.print("Numéro de place: ");
        String numeroPlace = scanner.nextLine();

        // Sélection du type de place
        System.out.println("Types de place disponibles:");
        for (TypePlace tp : TypePlace.values()) {
            System.out.println((tp.ordinal() + 1) + ". " + tp);
        }
        System.out.print("Choix: ");
        int typeIndex = Integer.parseInt(scanner.nextLine()) - 1;
        TypePlace typePlace = TypePlace.values()[typeIndex];

        // Création du billet
        Billet billet = new Billet(nextBilletId++, numeroPlace, client, event, typePlace);
        billets.add(billet);
        event.ajouterBillet(billet);
        client.ajouterBillet(billet);

        System.out.println("Billet réservé avec ID: " + billet.getId());
        System.out.println("Places restantes: " + event.getPlacesDisponibles());
    }

    private void listerBillets() {
        if (billets.isEmpty()) {
            System.out.println("Aucun billet enregistré.");
            return;
        }

        for (Billet b : billets) {
            System.out.println(b);
        }
    }

    private void modifierBillet() throws NotFoundException {
        listerBillets();
        if (billets.isEmpty()) return;

        System.out.print("ID du billet à modifier: ");
        int id = Integer.parseInt(scanner.nextLine());
        Billet billet = trouverBillet(id);

        System.out.print("Nouveau numéro de place (" + billet.getNumeroPlace() + "): ");
        String numero = scanner.nextLine();
        if (!numero.isEmpty()) billet.setNumeroPlace(numero);

        System.out.println("Nouveau type de place:");
        for (TypePlace tp : TypePlace.values()) {
            System.out.println((tp.ordinal() + 1) + ". " + tp);
        }
        System.out.print("Choix (enter pour ne pas changer): ");
        String typeStr = scanner.nextLine();
        if (!typeStr.isEmpty()) {
            TypePlace type = TypePlace.values()[Integer.parseInt(typeStr) - 1];
            billet.setTypePlace(type);
        }

        System.out.println("Billet modifié!");
    }

    private void annulerBillet() throws NotFoundException {
        listerBillets();
        if (billets.isEmpty()) return;

        System.out.print("ID du billet à annuler: ");
        int id = Integer.parseInt(scanner.nextLine());
        Billet billet = trouverBillet(id);
        supprimerBillet(billet);
        System.out.println("Billet annulé!");
    }

    private void supprimerBillet(Billet billet) {
        billet.getEvenement().supprimerBillet(billet);
        billet.getClient().supprimerBillet(billet);
        billets.remove(billet);
    }

    // Méthodes utilitaires pour trouver les entités
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