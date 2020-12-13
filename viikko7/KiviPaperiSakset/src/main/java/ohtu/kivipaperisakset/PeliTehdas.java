package ohtu.kivipaperisakset;

public class PeliTehdas {
    protected PeliTehdas() {

    }

    public static KiviPaperiSakset palautaPeli(String pelityyppi) {
        if (pelityyppi.equals("a")) {
            return new KPSPelaajaVsPelaaja();
        } else if (pelityyppi.equals("b")) {
            return new KPSTekoaly(new TekoalyNormaali());
        } else if (pelityyppi.equals("c")) {
            return new KPSTekoaly(new TekoalyParannettu(20));
        } else {
            return null;
        }
    }

}
