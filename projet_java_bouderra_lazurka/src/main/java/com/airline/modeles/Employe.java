package com.airline.modeles;

import java.time.LocalDate;

// Employe hérite de Personne, c'est aussi une classe abstraite
// car on aura toujours soit un Pilote soit un PersonnelCabine, jamais juste "Employe"
public abstract class Employe extends Personne {

    private String numeroEmploye;
    private LocalDate dateEmbauche;

    public Employe() {}

    public Employe(String identifiant, String nom, String adresse, String contact,
                   String numeroEmploye, LocalDate dateEmbauche) {
        // on appelle le constructeur de Personne pour les champs communs
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    // chaque type d'employé retourne son rôle différemment
    // donc on force les sous-classes à implémenter cette méthode
    public abstract String obtenirRole();

    // on implémente obtenirInfos() ici pour afficher les infos communes aux employés
    // les sous-classes peuvent toujours la redéfinir si besoin
    @Override
    public void obtenirInfos() {
        System.out.println("====== Informations Employé ======");
        System.out.println(afficherInfosBase());
        System.out.println("Numéro employé  : " + numeroEmploye);
        System.out.println("Date d'embauche : " + dateEmbauche);
        System.out.println("Rôle            : " + obtenirRole());
        System.out.println("==================================");
    }

    @Override
    public String toString() {
        return "Employe{num='" + numeroEmploye + "', nom='" + getNom()
                + "', role='" + obtenirRole() + "'}";
    }

    // --- getters et setters ---

    public String getNumeroEmploye() { return numeroEmploye; }
    public void setNumeroEmploye(String numeroEmploye) {
        this.numeroEmploye = numeroEmploye;
    }

    public LocalDate getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
}
