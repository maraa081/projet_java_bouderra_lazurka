package com.airline.modeles;

import java.time.LocalDate;

// le personnel cabine, c'est les hôtesses et stewards
// ils ont une qualification en plus et peuvent eux aussi être affectés à un vol
public class PersonnelCabine extends Employe {

    private String qualification;

    // pareil que pour le pilote, on garde le vol sur lequel ils travaillent
    private Vol volAffecte;

    public PersonnelCabine() {}

    public PersonnelCabine(String identifiant, String nom, String adresse, String contact,
                           String numeroEmploye, LocalDate dateEmbauche,
                           String qualification) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.qualification = qualification;
    }

    // affecte ce membre du personnel à un vol
    public void affecterVol(Vol vol) {
        if (vol == null) {
            System.out.println("Erreur : vol invalide pour l'affectation.");
            return;
        }
        this.volAffecte = vol;
        System.out.println("Personnel cabine " + getNom()
                + " affecté au vol " + vol.getNumeroVol());
    }

    // affiche les infos du vol affecté
    public void obtenirVol() {
        if (volAffecte == null) {
            System.out.println("Aucun vol affecté à " + getNom() + ".");
            return;
        }
        volAffecte.obtenirVol(volAffecte.getNumeroVol());
    }

    @Override
    public String obtenirRole() {
        return "Personnel Cabine";
    }

    @Override
    public void obtenirInfos() {
        System.out.println("====== Informations Personnel Cabine ======");
        System.out.println(afficherInfosBase());
        System.out.println("Numéro employé  : " + getNumeroEmploye());
        System.out.println("Date d'embauche : " + getDateEmbauche());
        System.out.println("Qualification   : " + qualification);
        System.out.println("==========================================");
    }

    @Override
    public String toString() {
        return "PersonnelCabine{nom='" + getNom()
                + "', qualification='" + qualification + "'}";
    }

    // --- getters et setters ---

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Vol getVolAffecte() { return volAffecte; }
    public void setVolAffecte(Vol vol) { this.volAffecte = vol; }
}
