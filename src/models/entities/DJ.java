package models.entities;

public class DJ extends Artiste{
    private int dureeSet;

    protected DJ(){};

    public int getDureeSet() {
        return dureeSet;
    }

    protected void setDureeSet(int dureeSet) {
        this.dureeSet = dureeSet;
    }

    @Override
    public String getDescription(){
        return  "DJ - " + getNom() + " - set de  " + dureeSet + " min - " + getCachet() + " €";
    }
}
