package com.airline.services;

import com.airline.modeles.Avion;
import com.airline.modeles.Vol;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// gestion de la flotte d'avions
public class GestionAvions {

    private List<Avion> avions = new ArrayList<>();

    // ajoute un avion à la flotte
    public void ajouterAvion(Avion avion) {
        if (avion == null) {
            System.out.println("Erreur : avion null.");
            return;
        }
        if (chercherAvion(avion.getImmatriculation()) != null) {
            System.out.println("Avion " + avion.getImmatriculation()
                    + " déjà dans la flotte.");
            return;
        }
        avions.add(avion);
        System.out.println("Avion " + avion.getImmatriculation() + " ajouté à la flotte.");
    }

    // cherche un avion par son immatriculation
    public Avion chercherAvion(String immatriculation) {
        for (Avion a : avions) {
            if (a.getImmatriculation().equals(immatriculation)) {
                return a;
            }
        }
        return null;
    }

    // modifie les infos d'un avion
    // la capacité à -1 veut dire "on ne change pas"
    public void modifierAvion(String immatriculation, String nouveauModele,
                              int nouvelleCapacite) {
        Avion a = chercherAvion(immatriculation);
        if (a == null) {
            System.out.println("Avion " + immatriculation + " introuvable.");
            return;
        }
        if (nouveauModele    != null) a.setModele(nouveauModele);
        if (nouvelleCapacite > 0)     a.setCapacite(nouvelleCapacite);
        System.out.println("Avion " + immatriculation + " mis à jour.");
    }

    // supprime un avion de la flotte
    public void supprimerAvion(String immatriculation) {
        Avion a = chercherAvion(immatriculation);
        if (a == null) {
            System.out.println("Avion " + immatriculation + " introuvable.");
            return;
        }
        avions.remove(a);
        System.out.println("Avion " + immatriculation + " retiré de la flotte.");
    }

    // cherche et retourne le premier avion disponible sur un créneau donné
    public Avion trouverAvionDisponible(LocalDateTime depart, LocalDateTime arrivee) {
        for (Avion a : avions) {
            if (a.verifierDisponibilite(depart, arrivee)) {
                System.out.println("Avion disponible trouvé : " + a.getImmatriculation());
                return a;
            }
        }
        System.out.println("Aucun avion disponible sur ce créneau.");
        return null;
    }

    // affecte un avion spécifique à un vol
    public void affecterAvionAuVol(String immatriculation, Vol vol) {
        Avion a = chercherAvion(immatriculation);
        if (a == null) {
            System.out.println("Avion " + immatriculation + " introuvable.");
            return;
        }
        a.affecterVol(vol);
    }

    // affiche tous les avions de la flotte
    public void listerAvions() {
        if (avions.isEmpty()) {
            System.out.println("La flotte est vide pour l'instant.");
            return;
        }
        System.out.println("=== Flotte d'avions (" + avions.size() + ") ===");
        for (Avion a : avions) {
            System.out.println("• " + a);
        }
    }

    public List<Avion> getAvions() { return avions; }
}
