package tradearea.warehouse;

import org.springframework.stereotype.Service;
import tradearea.model.WarehouseData;  // Importiere die WarehouseData Klasse aus dem model Paket

@Service
public class WarehouseService {

    public String getGreetings(String inModule) {
        return "Greetings from " + inModule;
    }

    public WarehouseData getWarehouseData(String inID) {

        WarehouseSimulation simulation = new WarehouseSimulation(inID);
        return simulation.getData(inID);

    }

}
