package tradearea.warehouse;
import java.util.*;
import tradearea.model.*;

public class WarehouseSimulation {

	public static final String[] cities = {
			"Wien", "St. Pölten", "Linz", "Graz", "Salzburg", "Eisenstadt",
			"Klagenfurt", "Innsbruck", "Bregenz"
	};

	public static final String[] parties = {
			"ÖVP", "SPÖ", "FPÖ", "GRÜNE", "NEOS", "BIER", "GAZA", "LMP", "MFG", "KEINE"
	};

	private final Random rand;

	public WarehouseSimulation(String id) {
		Long seed = 0L;
		try {
			seed = Long.parseLong(id);
		} catch (NumberFormatException ex) {
			System.err.println(ex);
		}
		this.rand = new Random(seed);
	}

	private int getRandomInt(int inMinimum, int inMaximum) {
		return rand.nextInt(inMaximum - inMinimum) + inMinimum;
	}

	public WarehouseData getData(String inID) {
		String city = cities[getRandomInt(0, cities.length)];

		WarehouseData data = new WarehouseData();
		data.setWarehouseID(inID);
		data.setWarehouseName("Wahllokal in " + city);
		data.setWarehouseCity(city);
		data.setWarehouseCountry("Austria");

		int totalStimmen = 6416177; // Maximale Stimmen
		int allocatedStimmen = 0; // Stimmen, die den Parteien zugewiesen wurden

		for (String party : parties) {
			Parteien p = new Parteien();
			p.setParteienName(party);

			// 10% Chance, dass die Partei 0 Stimmen hat
			int stimmen = rand.nextInt(100) < 10 ? 0 : getRandomInt(0, totalStimmen - allocatedStimmen + 1);

			// Stelle sicher, dass wir die Stimmen so verteilen, dass mindestens 70% der Stimmen an Kandidaten gehen
			if (stimmen > 0) {
				allocatedStimmen += stimmen;
				p.setParteienStimmen(stimmen);
			} else {
				p.setParteienStimmen(0);
			}

			// Generiere Vorzugswahl-Kandidaten
			List<VorzugswahlKandidat> kandidaten = new ArrayList<>();
			int anzahlKandidaten = 3; // Anzahl der Kandidaten
			int kandidatenStimmen = 0;

			// Wenn die Partei 0 Stimmen hat, hat der Kandidat auch 0 Stimmen
			if (stimmen > 0) {
				// Zuweisung von 70% der Stimmen an die Kandidaten
				int gesamtKandidatenStimmen = (int) (stimmen * 0.7);
				int verbleibendeStimmen = gesamtKandidatenStimmen;

				for (int i = 1; i <= anzahlKandidaten; i++) {
					VorzugswahlKandidat kandidat = new VorzugswahlKandidat(party + " Kandidat " + i);
					// Zufällige Stimmenverteilung für Kandidaten
					int kandidatStimmen = getRandomInt(0, verbleibendeStimmen + 1);
					kandidat = new VorzugswahlKandidat(party + " Kandidat " + i, kandidatStimmen);
					kandidaten.add(kandidat);
					kandidatenStimmen += kandidatStimmen;
					verbleibendeStimmen -= kandidatStimmen; // Verbleibende Stimmen aktualisieren
				}

				// Wenn nach der Verteilung noch Stimmen übrig sind, diese dem letzten Kandidaten hinzufügen
				if (verbleibendeStimmen > 0 && kandidaten.size() > 0) {
					kandidaten.get(kandidaten.size() - 1).addStimmen(verbleibendeStimmen);
				}
			} else {
				for (int i = 1; i <= 3; i++) { // 3 Kandidaten pro Partei mit 0 Stimmen
					VorzugswahlKandidat kandidat = new VorzugswahlKandidat(party + " Kandidat " + i, 0);
					kandidaten.add(kandidat);
				}
			}

			// Stimmen der Kandidaten zur Partei addieren
			if (stimmen > 0) {
				for (VorzugswahlKandidat kandidat : kandidaten) {
					p.addKandidat(kandidat);
				}
			}

			data.addParteien(p); // Füge die Partei den Wahldaten hinzu
		}

		return data;
	}

}