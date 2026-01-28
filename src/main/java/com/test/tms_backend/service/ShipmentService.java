package com.test.tms_backend.service;

import com.test.tms_backend.model.Shipment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class ShipmentService {

    private final Map<String, Shipment> shipments = new LinkedHashMap<>();
    private int nextId = 1;

    public ShipmentService() {
        // Seed dummy data
        addShipment(new Shipment(null, "Amazon", "DHL", "Bangalore", "Chennai",
                "TRK123", "In Transit", 5000, LocalDateTime.now()));
        addShipment(new Shipment(null, "Flipkart", "FedEx", "Mumbai", "Pune",
                "TRK456", "Pending", 3000, LocalDateTime.now()));
    }

    // Add shipment
    public Shipment addShipment(Shipment shipment) {
        String id =  String.valueOf(nextId++);
        shipment.setId(id);
        shipment.setCreatedAt(LocalDateTime.now());
        shipments.put(id, shipment);
        return shipment;
    }

    // Update shipment
    public Shipment updateShipment(String id, String status, Double rate) {
        Shipment shipment = shipments.get(id);
        if (shipment == null) throw new RuntimeException("Shipment not found");
        if (status != null) shipment.setStatus(status);
        if (rate != null) shipment.setRate(rate);
        return shipment;
    }

    // Get all shipments with optional filters, pagination, sorting
    public List<Shipment> getShipments(Map<String, String> filter,
                                       int page, int size,
                                       String sortBy, String sortOrder) {

        List<Shipment> list = new ArrayList<>(shipments.values());

        // Filtering
        if (filter != null) {
            if (filter.containsKey("shipperName")) {
                String shipperName = filter.get("shipperName");
                list = list.stream()
                        .filter(s -> s.getShipperName().equalsIgnoreCase(shipperName))
                        .collect(Collectors.toList());
            }
            if (filter.containsKey("status")) {
                String status = filter.get("status");
                list = list.stream()
                        .filter(s -> s.getStatus().equalsIgnoreCase(status))
                        .collect(Collectors.toList());
            }
        }

        // Sorting
        list.sort((a, b) -> {
            int cmp;
            switch (sortBy) {
                case "rate":
                    cmp = Double.compare(a.getRate(), b.getRate());
                    break;
                case "createdAt":
                default:
                    cmp = a.getCreatedAt().compareTo(b.getCreatedAt());
            }
            return "desc".equalsIgnoreCase(sortOrder) ? -cmp : cmp;
        });

        // Pagination
        int fromIndex = Math.min((page - 1) * size, list.size());
        int toIndex = Math.min(fromIndex + size, list.size());

        return list.subList(fromIndex, toIndex);
    }

    // Get single shipment
    public Shipment getShipment(String id) {
        return shipments.get(id);
    }
}
