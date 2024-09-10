package com.example.computerstore.controllers;

import com.example.computerstore.controllers.TableController.FieldMeta;
import com.example.computerstore.models.RAM;
import com.example.computerstore.repository.RAMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rams")
public class RAMController {

    @Autowired
    private RAMRepository ramRepository;

    @PostMapping
    public ResponseEntity<RAM> addRAM(@RequestBody RAM ram) {
        RAM savedRam = ramRepository.save(ram);
        return ResponseEntity.ok(savedRam);
    }

    @GetMapping
    public List<RAM> getAllRAMs() {
        return ramRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RAM> getRAMById(@PathVariable Integer id) {
        Optional<RAM> ram = ramRepository.findById(id);
        return ram.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /* 
    @PostMapping
    public ResponseEntity<RAM> createRAM(@RequestBody RAM ram) {
        if (ram.getName() == null || ram.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(null); 
        }
        return ResponseEntity.ok(ramRepository.save(ram));
    }
    */

    @PutMapping("/{id}")
public ResponseEntity<RAM> updateRAM(@PathVariable Integer id, @RequestBody RAM ramDetails) {
    Optional<RAM> ram = ramRepository.findById(id);
    if (ram.isPresent()) {
        RAM updatedRAM = ram.get();
        updatedRAM.setName(ramDetails.getName());
        updatedRAM.setCapacity(ramDetails.getCapacity());
        updatedRAM.setFrequency(ramDetails.getFrequency());
        updatedRAM.setModuleCount(ramDetails.getModuleCount());
        updatedRAM.setType(ramDetails.getType());
        updatedRAM.setVoltage(ramDetails.getVoltage());
        updatedRAM.setTimingScheme(ramDetails.getTimingScheme());
        updatedRAM.setPrice(ramDetails.getPrice());
        updatedRAM.setPhotoUrl(ramDetails.getPhotoUrl());
        updatedRAM.setCategory(ramDetails.getCategory());
        return ResponseEntity.ok(ramRepository.save(updatedRAM));
    } else {
        return ResponseEntity.notFound().build();
    }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRAM(@PathVariable Integer id) {
        Optional<RAM> ram = ramRepository.findById(id);
        if (ram.isPresent()) {
            ramRepository.delete(ram.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fields")
    public ResponseEntity<List<FieldMeta>> getRAMFields() {
        List<FieldMeta> fields = new ArrayList<>();
        fields.add(new FieldMeta("name", "text", true));
        fields.add(new FieldMeta("capacity", "number", true));
        fields.add(new FieldMeta("type", "text", false));
        fields.add(new FieldMeta("voltage", "number", false));
        fields.add(new FieldMeta("timingScheme", "text", false));
        fields.add(new FieldMeta("frequency", "number", false));
        fields.add(new FieldMeta("moduleCount", "number", false));
        fields.add(new FieldMeta("price", "number", true));
        fields.add(new FieldMeta("photoUrl", "text", false));
        return ResponseEntity.ok(fields);
    }
}
