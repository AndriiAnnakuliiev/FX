package com.example.computerstore.controllers;

import com.example.computerstore.controllers.TableController.FieldMeta;
import com.example.computerstore.models.SSD;
import com.example.computerstore.repository.SSDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ssds")
public class SSDController {

    @Autowired
    private SSDRepository ssdRepository;

    @PostMapping
    public ResponseEntity<SSD> addSSD(@RequestBody SSD ssd) {
        SSD savedSSD = ssdRepository.save(ssd);
        return ResponseEntity.ok(savedSSD);
    }

    @GetMapping
    public List<SSD> getAllSSDs() {
        return ssdRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SSD> getSSDById(@PathVariable Integer id) {
        Optional<SSD> ssd = ssdRepository.findById(id);
        return ssd.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
public ResponseEntity<SSD> updateSSD(@PathVariable Integer id, @RequestBody SSD ssdDetails) {
    Optional<SSD> ssd = ssdRepository.findById(id);
    if (ssd.isPresent()) {
        SSD updatedSSD = ssd.get();
        updatedSSD.setName(ssdDetails.getName());
        updatedSSD.setCapacity(ssdDetails.getCapacity());
        updatedSSD.setReadSpeed(ssdDetails.getReadSpeed());
        updatedSSD.setWriteSpeed(ssdDetails.getWriteSpeed());
        updatedSSD.setFormFactor(ssdDetails.getFormFactor());
        updatedSSD.setInterfaceType(ssdDetails.getInterfaceType());
        updatedSSD.setMemoryType(ssdDetails.getMemoryType());
        updatedSSD.setPrice(ssdDetails.getPrice());
        updatedSSD.setPhotoUrl(ssdDetails.getPhotoUrl());
        updatedSSD.setCategory(ssdDetails.getCategory());
        return ResponseEntity.ok(ssdRepository.save(updatedSSD));
    } else {
        return ResponseEntity.notFound().build();
    }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSSD(@PathVariable Integer id) {
        Optional<SSD> ssd = ssdRepository.findById(id);
        if (ssd.isPresent()) {
            ssdRepository.delete(ssd.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/fields")
    public ResponseEntity<List<FieldMeta>> getSSDFields() {
        List<FieldMeta> fields = new ArrayList<>();
        fields.add(new FieldMeta("name", "text", true));
        fields.add(new FieldMeta("capacity", "number", true));
        fields.add(new FieldMeta("readSpeed", "number", false));
        fields.add(new FieldMeta("writeSpeed", "number", false));
        fields.add(new FieldMeta("formFactor", "text", false));
        fields.add(new FieldMeta("interfaceType", "text", false));
        fields.add(new FieldMeta("memoryType", "text", false));
        fields.add(new FieldMeta("price", "number", true));
        fields.add(new FieldMeta("photoUrl", "text", false));
        return ResponseEntity.ok(fields);
    }
}
