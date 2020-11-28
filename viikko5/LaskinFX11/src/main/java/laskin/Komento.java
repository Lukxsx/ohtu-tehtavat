package laskin;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public abstract class Komento {

    public Komento(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo, Sovelluslogiikka sovellus) {

    }

    public abstract void suorita();
}
