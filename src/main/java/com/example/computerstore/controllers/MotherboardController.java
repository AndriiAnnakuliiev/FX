package com.example.computerstore.controllers;

import com.example.computerstore.controllers.TableController.FieldMeta;
import com.example.computerstore.models.Motherboard;
import com.example.computerstore.repository.MotherboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motherboards")
public class MotherboardController {

    @Autowired
    private MotherboardRepository motherboardRepository;

    @GetMapping
    public List<Motherboard> getAllMotherboards() {
        return motherboardRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motherboard> getMotherboardById(@PathVariable Integer id) {
        Optional<Motherboard> motherboard = motherboardRepository.findById(id);
        return motherboard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Motherboard createMotherboard(@RequestBody Motherboard motherboard) {
        return motherboardRepository.save(motherboard);
    }

    @PutMapping("/{id}")
public ResponseEntity<Motherboard> updateMotherboard(@PathVariable Integer id, @RequestBody Motherboard motherboardDetails) {
    Optional<Motherboard> motherboard = motherboardRepository.findById(id);
    if (motherboard.isPresent()) {
        Motherboard updatedMotherboard = motherboard.get();
        updatedMotherboard.setName(motherboardDetails.getName());
        updatedMotherboard.setSocket(motherboardDetails.getSocket());
        updatedMotherboard.setRamType(motherboardDetails.getRamType());
        updatedMotherboard.setPcieEx(motherboardDetails.getPcieEx());
        updatedMotherboard.setFormFactor(motherboardDetails.getFormFactor());
        updatedMotherboard.setChipset(motherboardDetails.getChipset());
        updatedMotherboard.setIntegratedGraphics(motherboardDetails.getIntegratedGraphics());
        updatedMotherboard.setPrice(motherboardDetails.getPrice());
        updatedMotherboard.setPhotoUrl(motherboardDetails.getPhotoUrl());
        updatedMotherboard.setCategory(motherboardDetails.getCategory());
        return ResponseEntity.ok(motherboardRepository.save(updatedMotherboard));
    } else {
        return ResponseEntity.notFound().build();
    }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotherboard(@PathVariable Integer id) {
        Optional<Motherboard> motherboard = motherboardRepository.findById(id);
        if (motherboard.isPresent()) {
            motherboardRepository.delete(motherboard.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/fields")
    public ResponseEntity<List<FieldMeta>> getMotherboardFields() {
        List<FieldMeta> fields = new ArrayList<>();
        fields.add(new FieldMeta("name", "text", true));
        fields.add(new FieldMeta("socket", "text", false));
        fields.add(new FieldMeta("ramType", "text", false));
        fields.add(new FieldMeta("pcieEx", "text", false));
        fields.add(new FieldMeta("formFactor", "text", false));
        fields.add(new FieldMeta("chipset", "text", false));
        fields.add(new FieldMeta("integratedGraphics", "checkbox", false));
        fields.add(new FieldMeta("price", "number", true));
        fields.add(new FieldMeta("photoUrl", "text", false));
        return ResponseEntity.ok(fields);
    }
}
