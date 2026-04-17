package com.airline.services;

import com.airline.modeles.Employe;
import com.airline.modeles.Pilote;
import com.airline.modeles.PersonnelCabine;

import java.util.ArrayList;
import java.util.List;

// gestion des employés : pilotes et personnel cabine sont tous stockés ici
// on peut filtrer par type si besoin
public class GestionEmployes {

    private List<Employe> employes = new ArrayList<>();

    // ajoute un employé (pilote ou personnel cabine)
    public void ajouterEmploye(Employe employe) {
        if (employe == null) {
            System.out.println("Erreur : employé null.");
            return;
        }
        if (chercherEmploye(employe.getNumeroEmploye()) != null) {
            System.out.println("Employé " + employe.getNumeroEmploye()
                    + " déjà enregistré.");
            return;
        }
        employes.add(employe);
        System.out.println("Employé " + employe.getNom()
                + " (" + employe.obtenirRole() + ") ajouté.");
    }

    // cherche un employé par son numéro
    public Employe chercherEmploye(String numeroEmploye) {
        for (Employe e : employes) {
            if (e.getNumeroEmploye().equals(numeroEmploye)) {
                return e;
            }
        }
        return null;
    }

    // retourne le rôle d'un employé par son numéro
    // correspond à la méthode obtenirRole() du diagramme
    public String obtenirRole(String numeroEmploye) {
        Employe e = chercherEmploye(numeroEmploye);
        if (e == null) {
            System.out.println("Employé " + numeroEmploye + " introuvable.");
            return null;
        }
        return e.obtenirRole();
    }

    // modifie les infos d'un employé
    public void modifierEmploye(String numeroEmploye, String nouveauNom,
                                String nouvelleAdresse, String nouveauContact) {
        Employe e = chercherEmploye(numeroEmploye);
        if (e == null) {
            System.out.println("Employé " + numeroEmploye + " introuvable.");
            return;
        }
        if (nouveauNom      != null) e.setNom(nouveauNom);
        if (nouvelleAdresse != null) e.setAdresse(nouvelleAdresse);
        if (nouveauContact  != null) e.setContact(nouveauContact);
        System.out.println("Employé " + numeroEmploye + " mis à jour.");
    }

    // supprime un employé de la liste
    public void supprimerEmploye(String numeroEmploye) {
        Employe e = chercherEmploye(numeroEmploye);
        if (e == null) {
            System.out.println("Employé " + numeroEmploye + " introuvable.");
            return;
        }
        employes.remove(e);
        System.out.println("Employé " + numeroEmploye + " supprimé.");
    }

    // retourne uniquement les pilotes de la liste
    // utile quand on veut affecter un pilote à un vol
    public List<Pilote> getPilotes() {
        List<Pilote> pilotes = new ArrayList<>();
        for (Employe e : employes) {
            if (e instanceof Pilote) {
                pilotes.add((Pilote) e);
            }
        }
        return pilotes;
    }

    // retourne uniquement le personnel cabine
    public List<PersonnelCabine> getPersonnelCabine() {
        List<PersonnelCabine> liste = new ArrayList<>();
        for (Employe e : employes) {
            if (e instanceof PersonnelCabine) {
                liste.add((PersonnelCabine) e);
            }
        }
        return liste;
    }

    // affiche tous les employés
    public void listerEmployes() {
        if (employes.isEmpty()) {
            System.out.println("Aucun employé enregistré.");
            return;
        }
        System.out.println("=== Liste des employés (" + employes.size() + ") ===");
        for (Employe e : employes) {
            System.out.println("• " + e);
        }
    }

    public List<Employe> getEmployes() { return employes; }
}
