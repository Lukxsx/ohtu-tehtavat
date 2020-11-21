package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] lukujono;
    private int alkioita;

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei voi olla negatiivinen");
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioita = 0;
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

        lukujono[alkioita] = luku;
        alkioita++;

        if (alkioita % lukujono.length == 0) {
            int[] uusi = new int[alkioita + kasvatuskoko];
            kopioiTaulukko(lukujono, uusi);
            lukujono = uusi;
        }
        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioita; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioita; i++) {
            if (luku == lukujono[i]) {
                lukujono[i] = 0;
                siirraAlkioitaVasemmalle(i);
                alkioita--;
                return true;
            }
        }

        return false;
    }

    private void siirraAlkioitaVasemmalle(int kohta) {
        int edellinen;
        for (int i = kohta; i < alkioita - 1; i++) {
            edellinen = lukujono[i];
            lukujono[i] = lukujono[i + 1];
            lukujono[i + 1] = edellinen;
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }

    public int mahtavuus() {
        return alkioita;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < alkioita; i++) {
            sb.append(lukujono[i]);
            if (i != alkioita - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioita];
        System.arraycopy(lukujono, 0, taulu, 0, taulu.length);
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

        int[] tulokset = Arrays.stream(a.toIntArray())
                .distinct()
                .filter(x -> Arrays.stream(b.toIntArray()).anyMatch(y -> y == x))
                .toArray();

        for (int i = 0; i < tulokset.length; i++) {
            tulos.lisaa(tulokset[i]);
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
