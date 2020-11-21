package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] ljono;
    private int alkioidenLkm;

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei voi olla negatiivinen");
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
        
        if (alkioidenLkm % ljono.length == 0) {
            int[] uusi = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(ljono, uusi);
            ljono = uusi;
        }
        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                ljono[i] = 0;
                siirraAlkioitaVasemmalle(i);
                alkioidenLkm--;
                return true;
            }
        }

        return false;
    }
    
    private void siirraAlkioitaVasemmalle(int kohta) {
        int edellinen;
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
                edellinen = ljono[j];
                ljono[j] = ljono[j + 1];
                ljono[j + 1] = edellinen;
            }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < alkioidenLkm; i++) {
            sb.append(ljono[i]);
            if (i != alkioidenLkm - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(ljono, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko tulos = new IntJoukko(a.mahtavuus() + b.mahtavuus());
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            tulos.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            tulos.lisaa(bTaulu[i]);
        }
        return tulos;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko tulos = new IntJoukko(a.mahtavuus() + b.mahtavuus());
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    tulos.lisaa(bTaulu[j]);
                }
            }
        }
        return tulos;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko tulos = new IntJoukko(a.mahtavuus());
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            tulos.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            tulos.poista(bTaulu[i]);
        }

        return tulos;
    }

}
