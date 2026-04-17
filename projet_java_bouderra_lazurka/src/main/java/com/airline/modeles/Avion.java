package com.airline.modeles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// classe qui représente un avion de la flotte
// un avion peut être affecté à des vols, donc on garde l'historique
public class Avion {

    private String immatriculation;
    private String modele;
    private int capacite;

    // liste des vols auxquels cet avion a été (ou est) affecté
    // on en a besoin pour vérifier les disponibilités
    private List<Vol> volsAffectes = new ArrayList<>();

    public Avion() {}

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
    }

    // affecte cet avion à un vol donné
    // on vérifie d'abord que l'avion est dispo sur ce créneau
    public void affecterVol(Vol vol) {
        if (vol == null) {
            System.out.println("Erreur : vol invalide pour l'affectation.");
            return;
        }
        // on check la dispo avant d'affecter
        if (!verifierDisponibilite(vol.getDateHeureDepart(), vol.getDateHeureArrivee())) {
            System.out.println("L'avion " + immatriculation
                    + " n'est pas disponible sur ce créneau.");
            return;
        }
        vol.setAvion(this);
        volsAffectes.add(vol);
        System.out.println("Avion " + immatriculation
                + " affecté au vol " + vol.getNumeroVol());
    }

    // vérifie si l'avion est libre sur un créneau donné
    // on parcourt les vols existants pour voir s'il y a un chevauchement
    public boolean verifierDisponibilite(LocalDateTime depart, LocalDateTime arrivee) {
        for (Vol v : volsAffectes) {
            // si le vol est annulé on l'ignore
            if (v.getEtat().equalsIgnoreCase("Annulé")) continue;

            // on regarde si les créneaux se chevauchent
            boolean chevauchement = depart.isBefore(v.getDateHeureArrivee())
                    && arrivee.isAfter(v.getDateHeureDepart());
            if (chevauchement) {
                return false; // avion pas dispo
            }
        }
        return true; // aucun conflit trouvé, l'avion est dispo
    }

    // affiche les infos de l'avion
    public void afficherInfos() {
        System.out.println("====== Avion ======");
        System.out.println("Immatriculation : " + immatriculation);
        System.out.println("Modèle          : " + modele);
        System.out.println("Capacité        : " + capacite + " passagers");
        System.out.println("Vols affectés   : " + volsAffectes.size());
        System.out.println("===================");
    }

    @Override
    public String toString() {
        return "Avion{'" + immatriculation + "' - " + modele
                + " (" + capacite + " places)}";
    }

    // --- getters et setters ---

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public List<Vol> getVolsAffectes() { return volsAffectes; }
}
