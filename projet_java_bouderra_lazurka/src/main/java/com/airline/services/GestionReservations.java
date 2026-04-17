package com.airline.services;

import com.airline.modeles.Reservation;

import java.util.ArrayList;
import java.util.List;

// service qui centralise toutes les réservations du système
// en complément de la liste dans chaque passager
public class GestionReservations {

    private List<Reservation> reservations = new ArrayList<>();

    // ajoute une réservation à la liste centrale
    public void ajouterReservation(Reservation reservation) {
        if (reservation == null) {
            System.out.println("Erreur : réservation null.");
            return;
        }
        if (chercherReservation(reservation.getNumeroReservation()) != null) {
            System.out.println("La réservation "
                    + reservation.getNumeroReservation() + " existe déjà.");
            return;
        }
        reservations.add(reservation);
        System.out.println("Réservation " + reservation.getNumeroReservation()
                + " enregistrée.");
    }

    // cherche une réservation par son numéro
    public Reservation chercherReservation(String numeroReservation) {
        for (Reservation r : reservations) {
            if (r.getNumeroReservation().equals(numeroReservation)) {
                return r;
            }
        }
        return null;
    }

    // affiche les infos d'une réservation par son numéro
    public void obtenirReservation(String numeroReservation) {
        Reservation r = chercherReservation(numeroReservation);
        if (r == null) {
            System.out.println("Réservation " + numeroReservation + " introuvable.");
            return;
        }
        r.afficherInfos();
    }

    // confirme une réservation par son numéro
    public void confirmerReservation(String numeroReservation) {
        Reservation r = chercherReservation(numeroReservation);
        if (r == null) {
            System.out.println("Réservation " + numeroReservation + " introuvable.");
            return;
        }
        r.confirmerReservation();
    }

    // annule une réservation par son numéro
    public void annulerReservation(String numeroReservation) {
        Reservation r = chercherReservation(numeroReservation);
        if (r == null) {
            System.out.println("Réservation " + numeroReservation + " introuvable.");
            return;
        }
        r.annulerReservation();
    }

    // supprime une réservation de la liste
    public void supprimerReservation(String numeroReservation) {
        Reservation r = chercherReservation(numeroReservation);
        if (r == null) {
            System.out.println("Réservation " + numeroReservation + " introuvable.");
            return;
        }
        reservations.remove(r);
        System.out.println("Réservation " + numeroReservation + " supprimée.");
    }

    // affiche toutes les réservations du système
    public void listerReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation dans le système.");
            return;
        }
        System.out.println("=== Toutes les réservations ("
                + reservations.size() + ") ===");
        for (Reservation r : reservations) {
            r.afficherInfos();
        }
    }

    public List<Reservation> getReservations() { return reservations; }
}
