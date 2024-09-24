package tradearea.warehouse;


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

// Importiere die WarehouseData Klasse aus dem model Paket
import tradearea.model.Parteien;
import tradearea.model.WarehouseData;

import java.util.List;

@RestController
@CrossOrigin
public class WarehouseController {

    @Autowired
    private WarehouseService service;

    @RequestMapping("/")
    public String warehouseMain() {
        String mainPage = "This is the warehouse application! (DEZSYS_WAREHOUSE_REST) <br/><br/>" +
                "<a href='http://localhost:8080/warehouse/001/data'>Link to warehouse/001/data</a><br/>" +
                "<a href='http://localhost:8080/warehouse/001/xml'>Link to warehouse/001/xml</a><br/>" +
                "<a href='http://localhost:8080/warehouse/001/transfer'>Link to warehouse/001/transfer</a><br/>";
        return mainPage;
    }

    @RequestMapping(value = "/warehouse/{inID}/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public WarehouseData warehouseData(@PathVariable String inID) {
        return service.getWarehouseData(inID);
    }

    @RequestMapping(value = "/warehouse/{inID}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public WarehouseData warehouseDataXML(@PathVariable String inID) {
        return service.getWarehouseData(inID);
    }

    @RequestMapping("/warehouse/{inID}/transfer")
    public String warehouseTransfer(@PathVariable String inID) {
        return service.getGreetings("Warehouse.Transfer!");
    }

    @RequestMapping(value = "/warehouse/{inID}/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Parteien> filterParteien(@PathVariable String inID,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) Integer stimmen) {
        WarehouseData warehouseData = service.getWarehouseData(inID);
        return warehouseData.filterParteien(name, stimmen);
    }

}