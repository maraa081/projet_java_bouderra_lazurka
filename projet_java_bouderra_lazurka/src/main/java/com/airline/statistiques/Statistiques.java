package com.airline.statistiques;

import com.airline.modeles.Reservation;
import com.airline.modeles.Vol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// classe bonus pour générer quelques stats et rapports sur l'activité
// on travaille directement avec les listes des services
public class Statistiques {

    // compte le nombre de vols par état (Planifié, Annulé, etc.)
    public static void rapportVols(List<Vol> vols) {
        int planifies = 0, annules = 0, termines = 0, enCours = 0;

        for (Vol v : vols) {
            switch (v.getEtat()) {
                case Vol.ETAT_PLANIFIE  -> planifies++;
                case Vol.ETAT_ANNULE    -> annules++;
                case Vol.ETAT_TERMINE   -> termines++;
                case Vol.ETAT_EN_
