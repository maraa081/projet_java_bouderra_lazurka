package com.airline.services;

import com.airline.modeles.Passager;

import java.util.ArrayList;
import java.util.List;

// ce service gère toutes les opérations CRUD sur les passagers
// on stocke la liste ici et on fournit les méthodes pour la manipuler
public class GestionPassagers {

    // la liste de tous les passagers enregistrés dans le système
    private List<Passager> passagers = new ArrayList<>();

    // ajoute un nouveau passager à la liste
    // on vérifie qu'il n'existe pas déjà avec le même ID
    public void ajouterPassager(Passager passager) {
        if (passager == null) {
            System.out.println("Erreur : le passager est null.");
            return;
        }
        if (chercherPassager(passager.getIdentifiant()) != null) {
            System.out.println("Un passager avec l'ID "
                    + passager.getIdentifiant() + " existe déjà.");
            return;
        }
        passagers.add(passager);
        System.out.println("Passager " + passager.getNom() + " ajouté avec succès.");
    }

    // cherche un passager par son identifiant unique
    // retourne null si on trouve rien
    public Passager chercherPassager(String identifiant) {
        for (Passager p : passagers) {
            if (p.getIdentifiant().equals(identifiant)) {
                return p;
            }
        }
        return null;
    }

    // cherche un passager par son numéro de passeport
    public Passager chercherParPasseport(String passeport) {
        for (Passager p : passagers) {
            if (p.getPasseport().equals(passeport)) {
                return p;
            }
        }
        System.out.println("Aucun passager trouvé avec le passeport : " + passeport);
        return null;
    }

    // modifie les infos d'un passager existant
    // si un paramètre est null, on ne touche pas à ce champ
    public void modifierPassager(String identifiant, String nouveauNom,
                                 String nouvelleAdresse, String nouveauContact) {
        Passager p = chercherPassager(identifiant);
        if (p == null) {
            System.out.println("Passager " + identifiant + " introuvable.");
            return;
        }
        if (nouveauNom      != null) p.setNom(nouveauNom);
        if (nouvelleAdresse != null) p.setAdresse(nouvelleAdresse);
        if (nouveauContact  != null) p.setContact(nouveauContact);
        System.out.println("Passager " + identifiant + " mis à jour.");
    }

    // supprime un passager de la liste par son ID
    public void supprimerPassager(String identifiant) {
        Passager p = chercherPassager(identifiant);
        if (p == null) {
            System.out.println("Passager " + identifiant + " introuvable.");
            return;
        }
        passagers.remove(p);
        System.out.println("Passager " + identifiant + " supprimé.");
    }

    // affiche tous les passagers enregistrés
    public void listerPassagers() {
        if (passagers.isEmpty()) {
            System.out.println("Aucun passager enregistré pour l'instant.");
            return;
        }
        System.out.println("=== Liste des passagers (" + passagers.size() + ") ===");
        for (Passager p : passagers) {
            System.out.println("• " + p);
        }
    }

    public List<Passager> getPassagers() { return passagers; }
}
