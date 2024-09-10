package com.example.computerstore.controllers;

import com.example.computerstore.controllers.TableController.FieldMeta;
import com.example.computerstore.models.PowerSupply;
import com.example.computerstore.repository.PowerSupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/powersupplies")
public class PowerSupplyController {

    @Autowired
    private PowerSupplyRepository powerSupplyRepository;

    @PostMapping
    public ResponseEntity<PowerSupply> addPowerSupply(@RequestBody PowerSupply powerSupply) {
        PowerSupply savedSupply = powerSupplyRepository.save(powerSupply);
        return ResponseEntity.ok(savedSupply);
    }

    @GetMapping
    public List<PowerSupply> getAllPowerSupplies() {
        return powerSupplyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerSupply> getPowerSupplyById(@PathVariable Integer id) {
        Optional<PowerSupply> powerSupply = powerSupplyRepository.findById(id);
        return powerSupply.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /* 
    @PostMapping
    public PowerSupply createPowerSupply(@RequestBody PowerSupply powerSupply) {
        return powerSupplyRepository.save(powerSupply);
    }
    */
    @PutMapping("/{id}")
    public ResponseEntity<PowerSupply> updatePowerSupply(@PathVariable Integer id, @RequestBody PowerSupply powerSupplyDetails) {
        Optional<PowerSupply> powerSupply = powerSupplyRepository.findById(id);
        if (powerSupply.isPresent()) {
            PowerSupply updatedPowerSupply = powerSupply.get();
            updatedPowerSupply.setName(powerSupplyDetails.getName());
            updatedPowerSupply.setWattage(powerSupplyDetails.getWattage());
            updatedPowerSupply.setProtectionTechnologies(powerSupplyDetails.getProtectionTechnologies());
            updatedPowerSupply.setGpuConnectorType(powerSupplyDetails.getGpuConnectorType());
            updatedPowerSupply.setPrice(powerSupplyDetails.getPrice());
            updatedPowerSupply.setPhotoUrl(powerSupplyDetails.getPhotoUrl());
            updatedPowerSupply.setCategory(powerSupplyDetails.getCategory());
            return ResponseEntity.ok(powerSupplyRepository.save(updatedPowerSupply));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePowerSupply(@PathVariable Integer id) {
        Optional<PowerSupply> powerSupply = powerSupplyRepository.findById(id);
        if (powerSupply.isPresent()) {
            powerSupplyRepository.delete(powerSupply.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fields")
    public ResponseEntity<List<FieldMeta>> getPowerSupplyFields() {
        List<FieldMeta> fields = new ArrayList<>();
        fields.add(new FieldMeta("name", "text", true));
        fields.add(new FieldMeta("wattage", "number", true));
        fields.add(new FieldMeta("protectionTechnologies", "text", false));
        fields.add(new FieldMeta("gpuConnectorType", "text", false));
        fields.add(new FieldMeta("price", "number", true));
        fields.add(new FieldMeta("photoUrl", "text", false));
        return ResponseEntity.ok(fields);
    }
}
