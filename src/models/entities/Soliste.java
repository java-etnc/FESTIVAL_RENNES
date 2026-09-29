package models.entities;

import models.references.Instrument;

public class Soliste extends Artiste {

    private Instrument instrument;
    protected Soliste(){}

    protected Instrument getInstrument() {
        return instrument;
    }

    protected void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public String getDescription(){
        return  "Soliste - " + getNom() + " - " + instrument.name().toLowerCase() + " - " + getCachet() + " €";
    }
}
