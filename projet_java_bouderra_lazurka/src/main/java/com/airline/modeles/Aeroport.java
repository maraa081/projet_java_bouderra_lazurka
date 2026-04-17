package com.airline.modeles;

// classe simple qui représente un aéroport
// il peut être le point de départ ou d'arrivée d'un vol
public class Aeroport {

    private String nom;
    private String ville;
    private String description;

    public Aeroport() {}

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
    }

    // affecte cet aéroport à un vol, soit comme départ soit comme arrivée
    // le paramètre type permet de différencier les deux cas
    public void affecterVol(Vol vol, String type) {
        if (vol == null) {
            System.out.println("Erreur : vol invalide.");
            return;
        }
        if (type.equalsIgnoreCase("depart")) {
            vol.setAeroportDepart(this);
            System.out.println("Aéroport " + nom + " défini comme départ du vol "
                    + vol.getNumeroVol());
        } else if (type.equalsIgnoreCase("arrivee")) {
            vol.setAeroportArrivee(this);
            System.out.println("Aéroport " + nom + " défini comme arrivée du vol "
                    + vol.getNumeroVol());
        } else {
            System.out.println("Type invalide : utilisez 'depart' ou 'arrivee'.");
        }
    }

    // affiche les infos de l'aéroport
    public void afficherInfos() {
        System.out.println("====== Aéroport ======");
        System.out.println("Nom         : " + nom);
        System.out.println("Ville       : " + ville);
        System.out.println("Description : " + description);
        System.out.println("======================");
    }

    @Override
    public String toString() {
        return "Aeroport{'" + nom + "' - " + ville + "}";
    }

    // --- getters et setters ---

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
