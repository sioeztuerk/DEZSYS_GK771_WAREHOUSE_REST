package tradearea.model;

import java.util.*;

public class Parteien {

    private String parteienName;
    private int parteienStimmen;
    private List<VorzugswahlKandidat> kandidaten; // Liste der Vorzugswahl-Kandidaten

    public Parteien() {
        this.kandidaten = new ArrayList<>();
    }

    public String getParteienName() {
        return parteienName;
    }

    public void setParteienName(String parteienName) {
        this.parteienName = parteienName;
    }

    public int getParteienStimmen() {
        return parteienStimmen;
    }

    public void setParteienStimmen(int parteienStimmen) {
        this.parteienStimmen = parteienStimmen;
    }

    public List<VorzugswahlKandidat> getKandidaten() {
        return kandidaten;
    }

    public void addKandidat(VorzugswahlKandidat kandidat) {
        kandidaten.add(kandidat);
        // Stimmen zu den Parteien addieren
        this.parteienStimmen += kandidat.getStimmen();
    }

    @Override
    public String toString() {
        return String.format("Partei: %s, Stimmen: %d", parteienName, parteienStimmen);
    }
}
