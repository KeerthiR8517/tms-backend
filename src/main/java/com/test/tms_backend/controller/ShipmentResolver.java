package com.test.tms_backend.controller;

import com.test.tms_backend.model.Shipment;
import com.test.tms_backend.security.JwtUtil;
import com.test.tms_backend.service.ShipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;
import java.util.Map;

@Controller
//@CrossOrigin(origins = "*")
public class ShipmentResolver {

    private final ShipmentService service;

    public ShipmentResolver(ShipmentService service) {
        this.service = service;
    }

    // Queries (EMPLOYEE + ADMIN)
    @QueryMapping
    public List<Shipment> shipments(
            @Argument(name = "filter") Map<String, String> filter,
            @Argument int page,
            @Argument int size,
            @Argument String sortBy,
            @Argument String sortOrder) {
        return service.getShipments(filter, page, size, sortBy, sortOrder);
    }

    @QueryMapping
    public Shipment shipment(@Argument String id) {
        return service.getShipment(id);
    }

    // Mutations (ADMIN ONLY)
    @MutationMapping
    public Shipment addShipment(
            @Argument String shipperName,
            @Argument String carrierName,
            @Argument String pickupLocation,
            @Argument String deliveryLocation,
            @Argument String trackingNumber,
            @Argument String status,
            @Argument Double rate) {

        checkAdmin();
        Shipment shipment = new Shipment(null, shipperName, carrierName,
                pickupLocation, deliveryLocation, trackingNumber, status, rate, null);

        return service.addShipment(shipment);
    }

    @MutationMapping
    public Shipment updateShipment(
            @Argument String id,
            @Argument String status,
            @Argument Double rate) {

        checkAdmin();
        return service.updateShipment(id, status, rate);
    }

    @MutationMapping
    public String login(@Argument String role) {
        return JwtUtil.generateToken(role);
    }

    private void checkAdmin() {
        String role = com.test.tms_backend.security.AuthFilter.roleHolder.get();
        if (role == null || !role.equals("ADMIN")) {
            throw new RuntimeException("Only ADMIN allowed");
        }
    }
}
