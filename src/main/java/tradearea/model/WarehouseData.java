package tradearea.model;

import java.text.*;
import java.util.*;

public class WarehouseData {
	private String warehouseID;
	private String warehouseName;
	private String timestamp;
	private String warehouseAddress;
	private String warehousePostalCode;
	private String warehouseCity;
	private String warehouseCountry;
	private ArrayList<Parteien> parteienData;

	public WarehouseData() {
		this.parteienData = new ArrayList<>();
		this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}

	public void setWarehouseID(String warehouseID) {
		this.warehouseID = warehouseID;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public void setWarehouseCity(String warehouseCity) {
		this.warehouseCity = warehouseCity;
	}

	public void setWarehouseCountry(String warehouseCountry) {
		this.warehouseCountry = warehouseCountry;
	}

	public String getWarehouseID() {
		return warehouseID;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public String getWarehouseAddress() {
		return warehouseCity;
	}

	public String getWarehousePostalCode() {
		return warehouseAddress;
	}

	public String getWarehouseCountry() {
		return warehouseCountry;
	}

	public String getWarehouseCity() {
		return warehouseCity;
	}

	public void addParteien(Parteien p) {
		this.parteienData.add(p);
	}

	public ArrayList<Parteien> getParteienData() {
		return parteienData;
	}

	public List<Parteien> filterParteien(String nameFilter, Integer stimmenFilter) {
		List<Parteien> filteredParteien = new ArrayList<>();

		for (Parteien partei : parteienData) {
			boolean matchesName = (nameFilter == null || nameFilter.isEmpty() || partei.getParteienName().toLowerCase().contains(nameFilter.toLowerCase()));
			boolean matchesStimmen = (stimmenFilter == null || partei.getParteienStimmen() == stimmenFilter);

			if (matchesName && matchesStimmen) {
				filteredParteien.add(partei);
			}
		}

		return filteredParteien;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Wahllokal ID: " + warehouseID + "\nStadt: " + warehouseCity + "\nParteien und Stimmen:\n");
		for (Parteien p : parteienData) {
			result.append(p.toString()).append("\n");
		}
		return result.toString();
	}
}