package tradearea.model;

public class VorzugswahlKandidat {

    private String name;
    private int stimmen;

    public VorzugswahlKandidat(String name) {
        this.name = name;
        this.stimmen = (int) (Math.random() * 1000); // Zufällige Stimmen zwischen 0 und 1000
    }

    public VorzugswahlKandidat(String name, int stimmen) {
        this.name = name;
        this.stimmen = stimmen; // Stimmen setzen
    }

    public void addStimmen(int stimmen) {
        this.stimmen += stimmen; // Stimmen erhöhen
    }

    public String getName() {
        return name;
    }

    public int getStimmen() {
        return stimmen;
    }

    @Override
    public String toString() {
        return String.format("Kandidat: %s, Stimmen: %d", name, stimmen);
    }
}
