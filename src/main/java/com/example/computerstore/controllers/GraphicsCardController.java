package com.example.computerstore.controllers;

import com.example.computerstore.controllers.TableController.FieldMeta;
import com.example.computerstore.models.GraphicsCard;
import com.example.computerstore.repository.GraphicsCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/graphicscards")
public class GraphicsCardController {

    @Autowired
    private GraphicsCardRepository graphicsCardRepository;


    @PostMapping("/add")
    public ResponseEntity<GraphicsCard> addGraphicsCard(@RequestBody GraphicsCard graphicsCard) {
        GraphicsCard savedCard = graphicsCardRepository.save(graphicsCard);
        return ResponseEntity.ok(savedCard);
    }

    @GetMapping
    public List<GraphicsCard> getAllGraphicsCards() {
        return graphicsCardRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GraphicsCard> getGraphicsCardById(@PathVariable Integer id) {
        Optional<GraphicsCard> graphicsCard = graphicsCardRepository.findById(id);
        return graphicsCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public GraphicsCard createGraphicsCard(@RequestBody GraphicsCard graphicsCard) {
        return graphicsCardRepository.save(graphicsCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GraphicsCard> updateGraphicsCard(@PathVariable Integer id, @RequestBody GraphicsCard graphicsCardDetails) {
        Optional<GraphicsCard> graphicsCard = graphicsCardRepository.findById(id);
        if (graphicsCard.isPresent()) {
            GraphicsCard updatedGraphicsCard = graphicsCard.get();
            updatedGraphicsCard.setName(graphicsCardDetails.getName());
            updatedGraphicsCard.setChipset(graphicsCardDetails.getChipset());
            updatedGraphicsCard.setMemorySize(graphicsCardDetails.getMemorySize());
            updatedGraphicsCard.setMemoryBus(graphicsCardDetails.getMemoryBus());
            updatedGraphicsCard.setAdditionalPower(graphicsCardDetails.getAdditionalPower());
            updatedGraphicsCard.setPcieEx(graphicsCardDetails.getPcieEx());
            updatedGraphicsCard.setMemoryType(graphicsCardDetails.getMemoryType());
            updatedGraphicsCard.setPrice(graphicsCardDetails.getPrice());
            updatedGraphicsCard.setPhotoUrl(graphicsCardDetails.getPhotoUrl());
            updatedGraphicsCard.setCategory(graphicsCardDetails.getCategory());
            return ResponseEntity.ok(graphicsCardRepository.save(updatedGraphicsCard));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGraphicsCard(@PathVariable Integer id) {
        Optional<GraphicsCard> graphicsCard = graphicsCardRepository.findById(id);
        if (graphicsCard.isPresent()) {
            graphicsCardRepository.delete(graphicsCard.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fields")
    public ResponseEntity<List<FieldMeta>> getGraphicsCardFields() {
        List<FieldMeta> fields = new ArrayList<>();
        fields.add(new FieldMeta("name", "text", true));
        fields.add(new FieldMeta("chipset", "text", false));
        fields.add(new FieldMeta("memorySize", "number", true));
        fields.add(new FieldMeta("memoryBus", "text", false));
        fields.add(new FieldMeta("additionalPower", "checkbox", false));
        fields.add(new FieldMeta("pcieEx", "text", false));
        fields.add(new FieldMeta("memoryType", "text", false));
        fields.add(new FieldMeta("price", "number", true));
        fields.add(new FieldMeta("photoUrl", "text", false));
        return ResponseEntity.ok(fields);
    }
}
