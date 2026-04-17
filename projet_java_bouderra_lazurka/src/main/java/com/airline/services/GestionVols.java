package com.airline.services;

import com.airline.modeles.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// gestion des vols : planification, annulation, affectation d'équipage...
public class GestionVols {

    private List<Vol> vols = new ArrayList<>();

    // planifie un nouveau vol et l'ajoute à la liste
    public void planifierVol(Vol vol) {
        if (vol == null) {
            System.out.println("Erreur : vol null.");
            return;
        }
        if (chercherVol(vol.getNumeroVol()) != null) {
            System.out.println("Le vol " + vol.getNumeroVol() + " existe déjà.");
            return;
        }
        vol.planifierVol();
        vols.add(vol);
    }

    // cherche un vol par son numéro
    public Vol chercherVol(String numeroVol) {
        for (Vol v : vols) {
            if (v.getNumeroVol().equals(numeroVol)) {
                return v;
            }
        }
        return null;
    }

    // affiche les infos d'un vol par son numéro (correspond à obtenirVol() du diagramme)
    public void obtenirVol(String numeroVol) {
        Vol v = chercherVol(numeroVol);
        if (v == null) {
            System.out.println("Vol " + numeroVol + " introuvable.");
            return;
        }
        v.obtenirVol(numeroVol);
    }

    // annule un vol par son numéro
    public void annulerVol(String numeroVol) {
        Vol v = chercherVol(numeroVol);
        if (v == null) {
            System.out.println("Vol " + numeroVol + " introuvable.");
            return;
        }
        v.annulerVol();
    }

    // modifie un vol existant
    public void modifierVol(String numeroVol, String origine,
                            String destination) {
        Vol v = chercherVol(numeroVol);
        if (v == null) {
            System.out.println("Vol " + numeroVol + " introuvable.");
            return;
        }
        v.modifierVol(origine, destination, null, null);
    }

    // affecte un équipage complet à un vol donné
    // correspond à affecterVol() du diagramme
    public void affecterEquipage(String numeroVol, Pilote pilote,
                                 List<PersonnelCabine> cabine) {
        Vol v = chercherVol(numeroVol);
        if (v == null) {
            System.out.println("Vol " + numeroVol + " introuvable.");
            return;
        }
        v.affecterEquipage(pilote, cabine);
    }

    // retourne tous les vols prévus pour une journée donnée
    // c'est notre fonctionnalité de "planning journalier"
    public List<Vol> getVolsParJour(LocalDate date) {
        List<Vol> resultat = new ArrayList<>();
        for (Vol v : vols) {
            if (v.getDateHeureDepart().toLocalDate().equals(date)) {
                resultat.add(v);
            }
        }
        return resultat;
    }

    // affiche le planning d'une journée donnée
    public void planningJournalier(LocalDate date) {
        List<Vol> volsDuJour = getVolsParJour(date);
        if (volsDuJour.isEmpty()) {
            System.out.println("Aucun vol planifié pour le " + date);
            return;
        }
        System.out.println("=== Planning du " + date
                + " (" + volsDuJour.size() + " vol(s)) ===");
        for (Vol v : volsDuJour) {
            System.out.println("• " + v);
        }
    }

    // affiche tous les vols du système
    public void listerVols() {
        if (vols.isEmpty()) {
            System.out.println("Aucun vol dans le système.");
            return;
        }
        System.out.println("=== Liste de tous les vols (" + vols.size() + ") ===");
        for (Vol v : vols) {
            System.out.println("• " + v);
        }
    }

    public List<Vol> getVols() { return vols; }
}
