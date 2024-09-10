package com.example.computerstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    @Autowired
    private SSDController ssdController;

    @Autowired
    private RAMController ramController;

    @Autowired
    private ProcessorController processorController;

    @Autowired
    private PowerSupplyController powerSupplyController;

    @Autowired
    private MotherboardController motherboardController;

    @Autowired
    private GraphicsCardController graphicsCardController;

    @GetMapping("/{tableName}/fields")
    public ResponseEntity<List<FieldMeta>> getTableFields(@PathVariable String tableName) {
        switch (tableName.toLowerCase()) {
            case "ssds":
                return ssdController.getSSDFields();
            case "rams":
                return ramController.getRAMFields();
            case "processors":
                return processorController.getProcessorFields();
            case "powersupplies":
                return powerSupplyController.getPowerSupplyFields();
            case "motherboards":
                return motherboardController.getMotherboardFields();
            case "graphicscards":
                return graphicsCardController.getGraphicsCardFields();
            default:
                return ResponseEntity.badRequest().body(null);
        }
    }

    static class FieldMeta {
        private String fieldName;
        private String fieldType;
        private boolean required;

        public FieldMeta(String fieldName, String fieldType, boolean required) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
            this.required = required;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public boolean isRequired() {
            return required;
        }
    }
}
