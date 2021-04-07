package com.openclassrooms.entrevoisins.controler.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.", generateFavoriteBool()),
            new Neighbour(2, "Jack", "https://i.pravatar.cc/150?u=a042581f4e29026704e", "Toulouse ; 5km",
                    "+33 6 35 50 25 29",  "Je me souviens en fait, je sais que, grâce à ma propre vérité le cycle du cosmos dans la vie. c'est une grande roue et cela même si les gens ne le savent pas !", generateFavoriteBool()),
            new Neighbour(3, "Chloé", "https://i.pravatar.cc/150?u=a042581f4e29026704f", "Bordeaux ; 5km",
                    "+33 6 33 98 88 23",  "Ah non attention, je sais que, grâce à ma propre vérité c'est juste une question d'awareness et c'est très, très beau d'avoir son propre moi-même ! Donc on n'est jamais seul spirituellement !", generateFavoriteBool()),
            new Neighbour(4, "Vincent", "https://i.pravatar.cc/150?u=a042581f4e29026704a", "Angoulème ; 5km",
                    "+33 6 38 41 78 68",  "J'ai vraiment une grande mission car il faut toute la splendeur du aware et finalement tout refaire depuis le début. C'est pour ça que j'ai fait des films avec des replicants.", generateFavoriteBool()),
            new Neighbour(5, "Elodie", "https://i.pravatar.cc/150?u=a042581f4e29026704b", "Pollestres ; 5km",
                    "+33 6 39 05 93 84",  "You see, ce n'est pas un simple sport car il faut se recréer... pour recréer... a better you car l'aboutissement de l'instinct, c'est l'amour ! Tu vas te dire : J'aurais jamais cru que le karaté guy pouvait parler comme ça !", generateFavoriteBool()),
            new Neighbour(6, "Sylvain", "https://i.pravatar.cc/150?u=a042581f4e29026704c", "Bages ; 5km",
                    "+33 6 39 36 72 00",  "Si vraiment tu veux te rappeler des souvenirs de ton perroquet, c'est juste une question d'awareness et c'est une sensation réelle qui se produit si on veut ! Il y a un an, je t'aurais parlé de mes muscles.", generateFavoriteBool()),
            new Neighbour(7, "Laetitia", "https://i.pravatar.cc/150?u=a042581f4e29026703d", "Paris ; 5km",
                    "+33 6 32 73 89 54",  "Ton grand frère, il s'est marié lui en tout cas, il sera toujours le meilleur, contrairement à toi ! Il en pouvait plus d'entendre Denise qu'il a fini dans la chambre !", generateFavoriteBool()),
            new Neighbour(8, "Dan", "https://i.pravatar.cc/150?u=a042581f4e29026703b", "Lilles ; 5km",
                    "+33 6 31 61 20 50",  "Ton frère, il s'est marié lui en tout cas, c'est un bon moyen de gagner sa vie, non ?! Le PMU était fermé alors il a fini chez le Jacquo !", generateFavoriteBool()),
            new Neighbour(9, "Joseph", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Colomiers ; 5km",
                    "+33 6 39 68 54 65",  "Ton grand-père, lui au moins fait du sport car c'est vraiment trop horrible pour mes oreilles. Et pépé, tu sais qu'il est parti hier dans la chambre !", generateFavoriteBool()),
            new Neighbour(10, "Emma", "https://i.pravatar.cc/150?u=a042581f4e29026706d", "Lyon ; 5km",
                    "+33 6 32 13 74 13",  "Toute personne a le droit de prendre part à la direction des affaires publiques de son pays, soit directement, soit par l'intermédiaire de représentants librement choisis.", generateFavoriteBool()),
            new Neighbour(11, "Patrick", "https://i.pravatar.cc/150?u=a042581f4e29026702d", "Nice ; 5km",
                    "+33 6 30 80 73 09",  "La volonté du peuple est le fondement de l'autorité des pouvoirs publics; cette volonté doit s'exprimer par des élections honnêtes qui doivent avoir lieu périodiquement, au suffrage universel égal et au vote secret ou suivant une procédure équivalente assurant la liberté du vote.", generateFavoriteBool()),
            new Neighbour(12, "Ludovic", "https://i.pravatar.cc/150?u=a042581f3e39026702d", "Banyuls-sur-mer ; 5km",
                    "+33 6 39 54 71 79",  "La maternité et l'enfance ont droit à une aide et à une assistance spéciales. Tous les enfants, qu'ils soient nés dans le mariage ou hors mariage, jouissent de la même protection sociale.", generateFavoriteBool())
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }

    static Boolean generateFavoriteBool() {
        // Code for dev intent - it allows to have random number of users already loaded in favorite page
        // Random r = new Random();
        // return r.nextBoolean();

        return false;
    }
}
