package com.airline.modeles;

// Personne est la classe de base pour tout le monde dans notre système
// (passagers, pilotes, personnel...). On la met abstraite car on ne crée
// jamais un objet "Personne" tout seul, c'est toujours un passager ou un employé
public abstract class Personne {

    // les infos de base communes à toutes les personnes
    private String identifiant;
    private String nom;
    private String adresse;
    private String contact;

    // constructeur vide, utile dans certains cas
    public Personne() {}

    // constructeur principal avec tous les champs
    public Personne(String identifiant, String nom, String adresse, String contact) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    // méthode abstraite : chaque sous-classe va l'implémenter à sa façon
    // pour afficher ses propres infos
    public abstract void obtenirInfos();

    // petite méthode utilitaire pour afficher les infos communes
    // on l'utilise dans les sous-classes pour pas réécrire la même chose
    public String afficherInfosBase() {
        return "Identifiant : " + identifiant + "\n"
                + "Nom         : " + nom         + "\n"
                + "Adresse     : " + adresse     + "\n"
                + "Contact     : " + contact;
    }

    @Override
    public String toString() {
        return "Personne{id='" + identifiant + "', nom='" + nom + "'}";
    }

    // --- getters et setters ---

    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
