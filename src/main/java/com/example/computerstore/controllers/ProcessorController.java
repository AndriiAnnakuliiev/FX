package com.example.computerstore.controllers;

import com.example.computerstore.controllers.TableController.FieldMeta;
import com.example.computerstore.models.Processor;
import com.example.computerstore.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/processors")
public class ProcessorController {

    @Autowired
    private ProcessorRepository processorRepository;

    @PostMapping
    public ResponseEntity<Processor> addProcessor(@RequestBody Processor processor) {
        Processor savedProcessor = processorRepository.save(processor);
        return ResponseEntity.ok(savedProcessor);
    }

    @GetMapping
    public List<Processor> getAllProcessors() {
        return processorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Processor> getProcessorById(@PathVariable Integer id) {
        Optional<Processor> processor = processorRepository.findById(id);
        return processor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    /* 
    @PostMapping
    public Processor createProcessor(@RequestBody Processor processor) {
        return processorRepository.save(processor);
    }
    */
    @PutMapping("/{id}")
public ResponseEntity<Processor> updateProcessor(@PathVariable Integer id, @RequestBody Processor processorDetails) {
    Optional<Processor> processor = processorRepository.findById(id);
    if (processor.isPresent()) {
        Processor updatedProcessor = processor.get();
        updatedProcessor.setName(processorDetails.getName());
        updatedProcessor.setSocket(processorDetails.getSocket());
        updatedProcessor.setCoreCount(processorDetails.getCoreCount());
        updatedProcessor.setThreadCount(processorDetails.getThreadCount());
        updatedProcessor.setBaseClock(processorDetails.getBaseClock());
        updatedProcessor.setBoostClock(processorDetails.getBoostClock());
        updatedProcessor.setL1Cache(processorDetails.getL1Cache());
        updatedProcessor.setL2Cache(processorDetails.getL2Cache());
        updatedProcessor.setL3Cache(processorDetails.getL3Cache());
        updatedProcessor.setPrice(processorDetails.getPrice());
        updatedProcessor.setPhotoUrl(processorDetails.getPhotoUrl());
        updatedProcessor.setCategory(processorDetails.getCategory());
        return ResponseEntity.ok(processorRepository.save(updatedProcessor));
    } else {
        return ResponseEntity.notFound().build();
    }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcessor(@PathVariable Integer id) {
        Optional<Processor> processor = processorRepository.findById(id);
        if (processor.isPresent()) {
            processorRepository.delete(processor.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fields")
    public ResponseEntity<List<FieldMeta>> getProcessorFields() {
        List<FieldMeta> fields = new ArrayList<>();
        fields.add(new FieldMeta("name", "text", true));
        fields.add(new FieldMeta("socket", "text", false));
        fields.add(new FieldMeta("coreCount", "number", true));
        fields.add(new FieldMeta("threadCount", "number", true));
        fields.add(new FieldMeta("baseClock", "number", false));
        fields.add(new FieldMeta("boostClock", "number", false));
        fields.add(new FieldMeta("l1Cache", "number", false));
        fields.add(new FieldMeta("l2Cache", "number", false));
        fields.add(new FieldMeta("l3Cache", "number", false));
        fields.add(new FieldMeta("price", "number", true));
        fields.add(new FieldMeta("photoUrl", "text", false));
        return ResponseEntity.ok(fields);
    }
}
