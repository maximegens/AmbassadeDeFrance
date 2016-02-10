package com.maximegens.ambassadedefrance.Beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ambassades {

    public static ArrayList<Ambassade> lesAmbassades = new ArrayList<>();

    static{
        //Creation de la liste de test des ambassades
        lesAmbassades.add(new Ambassade(0,"Ambassade Lille"));
        lesAmbassades.add(new Ambassade(1,"Ambassade Paris"));
        lesAmbassades.add(new Ambassade(2,"Ambassade Lyon"));
        lesAmbassades.add(new Ambassade(3,"Ambassade Marseille"));
        lesAmbassades.add(new Ambassade(4,"Ambassade Boulogne Sur Mer"));
    }

    /**
     * Retourne le restaurant correspondant à l'ID passé en paramétre.
     *
     * @param id L'ID du restaurant.
     * @return Le Léon de Bruxelles correspondant à l'ID.
     */
    public Ambassade getAmbassadesById(int id) {
        for (Ambassade ambassade : lesAmbassades) {
            if (ambassade.getId() == id)
                return ambassade;
        }
        return null;
    }

}


