package com.airline.modeles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// classe centrale du système, un vol relie deux aéroports
// il a un avion, un équipage et une liste de passagers
public class Vol {

    // quelques constantes pour les états possibles d'un vol
    public static final String ETAT_PLANIFIE  = "Planifié";
    public static final String ETAT_EN_COURS  = "En cours";
    public static final String ETAT_TERMINE   = "Terminé";
    public static final String ETAT_ANNULE    = "Annulé";

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String numeroVol;
    private String origine;
    private String destination;
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateHeureArrivee;
    private String etat;

    // l'avion utilisé pour ce vol
    private Avion avion;

    // les aéroports de départ et d'arrivée
    private Aeroport aeroportDepart;
    private Aeroport aeroportArrivee;

    // le pilote affecté à ce vol
    private Pilote pilote;

    // l'équipe cabine affectée à ce vol (plusieurs membres possibles)
    private List<PersonnelCabine> equipeCabine = new ArrayList<>();

    // la liste des passagers qui ont réservé ce vol
    private List<Passager> passagers = new ArrayList<>();

    public Vol() {
        this.etat = ETAT_PLANIFIE;
    }

    public Vol(String numeroVol, String origine, String destination,
               LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = ETAT_PLANIFIE;
    }

    // planifie le vol, on met juste son état à "Planifié"
    public void planifierVol() {
        this.etat = ETAT_PLANIFIE;
        System.out.println("Vol " + numeroVol + " planifié : "
                + origine + " → " + destination
                + " le " + dateHeureDepart.format(FORMATTER));
    }

    // annule le vol et prévient si des passagers sont affectés
    public void annulerVol() {
        if (ETAT_ANNULE.equals(this.etat)) {
            System.out.println("Le vol " + numeroVol + " est déjà annulé.");
            return;
        }
        this.etat = ETAT_ANNULE;
        System.out.println("Vol " + numeroVol + " annulé.");
        // on informe sur le nombre de passagers impactés
        if (!passagers.isEmpty()) {
            System.out.println(passagers.size()
                    + " passager(s) impacté(s) par l'annulation.");
        }
    }

    // modifie les infos d'un vol (départ, arrivée, horaires...)
    public void modifierVol(String nouvelleOrigine, String nouvelleDestination,
                            LocalDateTime nouveauDepart, LocalDateTime nouvelleArrivee) {
        if (ETAT_ANNULE.equals(this.etat)) {
            System.out.println("Impossible de modifier un vol annulé.");
            return;
        }
        // on ne modifie que les champs fournis (si null on garde l'ancien)
        if (nouvelleOrigine    != null) this.origine = nouvelleOrigine;
        if (nouvelleDestination != null) this.destination = nouvelleDestination;
        if (nouveauDepart      != null) this.dateHeureDepart = nouveauDepart;
        if (nouvelleArrivee    != null) this.dateHeureArrivee = nouvelleArrivee;
        System.out.println("Vol " + numeroVol + " modifié.");
    }

    // affiche les informations d'un vol par son numéro
    public void obtenirVol(String numeroVol) {
        if (!this.numeroVol.equals(numeroVol)) {
            System.out.println("Numéro de vol incorrect.");
            return;
        }
        System.out.println("====== Vol " + this.numeroVol + " ======");
        System.out.println("Origine      : " + origine);
        System.out.println("Destination  : " + destination);
        System.out.println("Départ       : " + dateHeureDepart.format(FORMATTER));
        System.out.println("Arrivée      : " + dateHeureArrivee.format(FORMATTER));
        System.out.println("État         : " + etat);
        System.out.println("Avion        : "
                + (avion != null ? avion.getImmatriculation() : "non affecté"));
        System.out.println("Pilote       : "
                + (pilote != null ? pilote.getNom() : "non affecté"));
        System.out.println("Équipe cabine: " + equipeCabine.size() + " membre(s)");
        System.out.println("Passagers    : " + passagers.size());
        System.out.println("===========================");
    }

    // affiche la liste des passagers de ce vol
    public void listingPassagers() {
        if (passagers.isEmpty()) {
            System.out.println("Aucun passager sur le vol " + numeroVol + ".");
            return;
        }
        System.out.println("=== Passagers du vol " + numeroVol + " ===");
        for (Passager p : passagers) {
            System.out.println("• " + p.getNom() + " (passeport: " + p.getPasseport() + ")");
        }
        System.out.println("Nombre total : " + passagers.size());
    }

    // affecte un équipage complet à ce vol (pilote + membres cabine)
    public void affecterEquipage(Pilote pilote, List<PersonnelCabine> cabine) {
        if (pilote == null) {
            System.out.println("Erreur : il faut au moins un pilote.");
            return;
        }
        this.pilote = pilote;
        pilote.affecterVol(this);

        // on ajoute chaque membre de l'équipe cabine
        if (cabine != null && !cabine.isEmpty()) {
            for (PersonnelCabine pc : cabine) {
                equipeCabine.add(pc);
                pc.affecterVol(this);
            }
        }
        System.out.println("Équipage affecté au vol " + numeroVol);
    }

    // ajoute un passager à la liste du vol
    // cette méthode est appelée depuis Passager.reserverVol()
    public void ajouterPassager(Passager passager) {
        if (passager != null && !passagers.contains(passager)) {
            passagers.add(passager);
        }
    }

    @Override
    public String toString() {
        return "Vol{'" + numeroVol + "' " + origine + "→" + destination
                + " [" + etat + "]}";
    }

    // --- getters et setters ---

    public String getNumeroVol() { return numeroVol; }
    public void setNumeroVol(String numeroVol) { this.numeroVol = numeroVol; }

    public String getOrigine() { return origine; }
    public void setOrigine(String origine) { this.origine = origine; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDateTime getDateHeureDepart() { return dateHeureDepart; }
    public void setDateHeureDepart(LocalDateTime dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public LocalDateTime getDateHeureArrivee() { return dateHeureArrivee; }
    public void setDateHeureArrivee(LocalDateTime dateHeureArrivee) {
        this.dateHeureArrivee = dateHeureArrivee;
    }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }

    public Pilote getPilote() { return pilote; }
    public void setPilote(Pilote pilote) { this.pilote = pilote; }

    public List<PersonnelCabine> getEquipeCabine() { return equipeCabine; }
    public void setEquipeCabine(List<PersonnelCabine> equipeCabine) {
        this.equipeCabine = equipeCabine;
    }

    public List<Passager> getPassagers() { return passagers; }
    public void setPassagers(List<Passager> passagers) { this.passagers = passagers; }

    public Aeroport getAeroportDepart() { return aeroportDepart; }
    public void setAeroportDepart(Aeroport aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }

    public Aeroport getAeroportArrivee() { return aeroportArrivee; }
    public void setAeroportArrivee(Aeroport aeroportArrivee) {
        this.aeroportArrivee = aeroportArrivee;
    }
}
