package exceptions.models;

/*
 * Exception contrôlée : la façade la lève (throw + throws dans l'interface
 * ET la classe), le Presenter l'attrape. Modèle : LISEZ-MOI.md, partie 3.
 */
public class ModelFacadeException extends Exception {

    public ModelFacadeException(String message) {
        super(message);
    }
}
