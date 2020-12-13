package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperiSakset {

    private Tekoaly tekoaly;

    public KPSTekoaly(Tekoaly tekoaly) {
        this.tekoaly = tekoaly;
    }

    @Override
    protected String toisenSiirto() {
        String siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }
}