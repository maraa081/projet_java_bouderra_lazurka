package com.airline.modeles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// une réservation lie un passager à un vol
// elle a un statut qui évolue au fil du temps
public class Reservation {

    // les trois états possibles d'une réservation
    public static final String STATUT_EN_ATTENTE = "En attente";
    public static final String STATUT_CONFIRME   = "Confirmé";
    public static final String STATUT_ANNULE     = "Annulé";

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String numeroReservation;
    private LocalDate dateReservation;
    private String statut;

    // les deux objets associés à cette réservation
    private Passager passager;
    private Vol vol;

    public Reservation() {
        this.dateReservation = LocalDate.now();
        this.statut = STATUT_EN_ATTENTE;
    }

    public Reservation(String numeroReservation, Passager passager, Vol vol) {
        this.numeroReservation = numeroReservation;
        this.passager = passager;
        this.vol = vol;
        this.dateReservation = LocalDate.now(); // date du jour automatiquement
        this.statut = STATUT_EN_ATTENTE;         // en attente par défaut
    }

    // confirme la réservation, on vérifie qu'elle n'est pas déjà annulée avant
    public void confirmerReservation() {
        if (STATUT_ANNULE.equals(this.statut)) {
            System.out.println("Impossible de confirmer la réservation "
                    + numeroReservation + " : elle est annulée.");
            return;
        }
        this.statut = STATUT_CONFIRME;
        System.out.println("Réservation " + numeroReservation + " confirmée.");
    }

    // annule la réservation
    public void annulerReservation() {
        if (STATUT_ANNULE.equals(this.statut)) {
            System.out.println("La réservation " + numeroReservation
                    + " est déjà annulée.");
            return;
        }
        this.statut = STATUT_ANNULE;
        System.out.println("Réservation " + numeroReservation + " annulée.");
    }

    // permet de changer le vol d'une réservation existante
    public void modifierReservation(Vol nouveauVol) {
        if (STATUT_ANNULE.equals(this.statut)) {
            System.out.println("Impossible de modifier une réservation annulée.");
            return;
        }
        if (nouveauVol == null) {
            System.out.println("Erreur : le nouveau vol est invalide.");
            return;
        }
        this.vol = nouveauVol;
        // on repasse en attente après une modification, logique
        this.statut = STATUT_EN_ATTENTE;
        System.out.println("Réservation " + numeroReservation
                + " modifiée → nouveau vol : " + nouveauVol.getNumeroVol());
    }

    // affiche toutes les infos de la réservation
    public void afficherInfos() {
        System.out.println("--- Réservation " + numeroReservation + " ---");
        System.out.println("Date       : " + dateReservation.format(FORMATTER));
        System.out.println("Statut     : " + statut);
        System.out.println("Passager   : "
                + (passager != null ? passager.getNom() : "non défini"));
        if (vol != null) {
            System.out.println("Vol        : " + vol.getNumeroVol()
                    + " (" + vol.getOrigine()
                    + " → " + vol.getDestination() + ")");
        }
        System.out.println("-------------------------------");
    }

    @Override
    public String toString() {
        return "Reservation{num='" + numeroReservation
                + "', statut='" + statut + "'}";
    }

    // --- getters et setters ---

    public String getNumeroReservation() { return numeroReservation; }
    public void setNumeroReservation(String n) { this.numeroReservation = n; }

    public LocalDate getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDate d) { this.dateReservation = d; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Passager getPassager() { return passager; }
    public void setPassager(Passager passager) { this.passager = passager; }

    public Vol getVol() { return vol; }
    public void setVol(Vol vol) { this.vol = vol; }
}
