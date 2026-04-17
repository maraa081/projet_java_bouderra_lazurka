package com.airline.modeles;

import java.util.ArrayList;
import java.util.List;

// un passager hérite aussi de Personne
// il a un passeport et peut faire plusieurs réservations
public class Passager extends Personne {

    private String passeport;

    // liste des réservations du passager, on initialise direct pour éviter les NullPointer
    private List<Reservation> reservations = new ArrayList<>();

    public Passager() {}

    public Passager(String identifiant, String nom, String adresse,
                    String contact, String passeport) {
        super(identifiant, nom, adresse, contact);
        this.passeport = passeport;
    }

    // méthode principale : le passager réserve un vol
    // on crée une réservation et on l'ajoute à sa liste
    public Reservation reserverVol(Vol vol, String numeroReservation) {
        if (vol == null) {
            System.out.println("Erreur : impossible de réserver un vol inexistant.");
            return null;
        }
        // on vérifie que le vol n'est pas annulé avant de continuer
        if (vol.getEtat().equalsIgnoreCase("Annulé")) {
            System.out.println("Le vol " + vol.getNumeroVol()
                    + " est annulé, réservation impossible.");
            return null;
        }

        // on crée la réservation et on l'associe à ce passager et ce vol
        Reservation reservation = new Reservation(numeroReservation, this, vol);
        reservations.add(reservation);

        // on ajoute aussi le passager dans la liste des passagers du vol
        vol.ajouterPassager(this);

        System.out.println("Réservation " + numeroReservation + " créée pour "
                + getNom() + " sur le vol " + vol.getNumeroVol());
        return reservation;
    }

    // annule une réservation en la cherchant par son numéro
    public void annulerReservation(String numeroReservation) {
        Reservation res = trouverReservation(numeroReservation);
        if (res == null) {
            System.out.println("Réservation " + numeroReservation + " introuvable.");
            return;
        }
        res.annulerReservation();
    }

    // affiche toutes les réservations de ce passager
    public void obtenirReservations() {
        if (reservations.isEmpty()) {
            System.out.println(getNom() + " n'a aucune réservation pour l'instant.");
            return;
        }
        System.out.println("=== Réservations de " + getNom() + " ===");
        for (Reservation r : reservations) {
            r.afficherInfos();
        }
    }

    // méthode privée pour chercher une réservation dans la liste par son numéro
    public Reservation trouverReservation(String numeroReservation) {
        for (Reservation r : reservations) {
            if (r.getNumeroReservation().equals(numeroReservation)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public void obtenirInfos() {
        System.out.println("====== Informations Passager ======");
        System.out.println(afficherInfosBase());
        System.out.println("Passeport       : " + passeport);
        System.out.println("Nb réservations : " + reservations.size());
        System.out.println("===================================");
    }

    @Override
    public String toString() {
        return "Passager{nom='" + getNom() + "', passeport='" + passeport + "'}";
    }

    // --- getters et setters ---

    public String getPasseport() { return passeport; }
    public void setPasseport(String passeport) { this.passeport = passeport; }

    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
