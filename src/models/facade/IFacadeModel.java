package models.facade;

import models.entities.Scene;

import java.util.List;

/*
 * Le seul accès du Presenter au métier. Toute méthode qui alimente un menu
 * renvoie une List. Un "throws" se recopie ici ET dans FacadeModel.
 */
public interface IFacadeModel {
    List<Scene> getScenes();
    void enregistrerScene(Scene scene);
}
