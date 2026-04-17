package com.airline.modeles;

import java.time.LocalDate;

// Pilote hérite d'Employe, il a en plus une licence et des heures de vol
// il peut aussi être affecté à un vol
public class Pilote extends Employe {

    private String licence;
    private int heuresDeVol;

    // on garde une référence vers le vol auquel ce pilote est affecté
    // c'est une association simple (pas une liste, un pilote = un vol à la fois)
    private Vol volAffecte;

    public Pilote() {}

    public Pilote(String identifiant, String nom, String adresse, String contact,
                  String numeroEmploye, LocalDate dateEmbauche,
                  String licence, int heuresDeVol) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
    }

    // affecte ce pilote à un vol donné
    public void affecterVol(Vol vol) {
        if (vol == null) {
            System.out.println("Erreur : le vol fourni est invalide.");
            return;
        }
        this.volAffecte = vol;
        System.out.println("Pilote " + getNom() + " affecté au vol " + vol.getNumeroVol());
    }

    // affiche les infos du vol affecté à ce pilote
    public void obtenirVol() {
        if (volAffecte == null) {
            System.out.println("Aucun vol affecté au pilote " + getNom() + " pour l'instant.");
            return;
        }
        // on délègue l'affichage à la méthode de Vol
        volAffecte.obtenirVol(volAffecte.getNumeroVol());
    }

    // retourne le rôle, ici c'est toujours "Pilote"
    @Override
    public String obtenirRole() {
        return "Pilote";
    }

    @Override
    public void obtenirInfos() {
        System.out.println("====== Informations Pilote ======");
        System.out.println(afficherInfosBase());
        System.out.println("Numéro employé  : " + getNumeroEmploye());
        System.out.println("Date d'embauche : " + getDateEmbauche());
        System.out.println("Licence         : " + licence);
        System.out.println("Heures de vol   : " + heuresDeVol + "h");
        System.out.println("=================================");
    }

    @Override
    public String toString() {
        return "Pilote{nom='" + getNom() + "', licence='" + licence
                + "', heures=" + heuresDeVol + "}";
    }

    // --- getters et setters ---

    public String getLicence() { return licence; }
    public void setLicence(String licence) { this.licence = licence; }

    public int getHeuresDeVol() { return heuresDeVol; }
    public void setHeuresDeVol(int heuresDeVol) { this.heuresDeVol = heuresDeVol; }

    public Vol getVolAffecte() { return volAffecte; }
    public void setVolAffecte(Vol vol) { this.volAffecte = vol; }
}
