package start;

import models.entities.Artiste;
import models.entities.Soliste;
import models.references.Instrument;
import presenter.Presenter;

public class ProgMain {
     static void main(String[] args) {
        Presenter presenter = new Presenter();
        presenter.start();
    }
}
