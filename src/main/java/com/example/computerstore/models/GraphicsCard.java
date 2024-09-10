package com.example.computerstore.models;

import javax.persistence.*;

@Entity
@Table(name = "graphics_cards")
public class GraphicsCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String chipset;
    private Integer memorySize;
    private String memoryBus;
    private Boolean additionalPower;
    private String pcieEx;
    private String memoryType;
    private Double price;
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public GraphicsCard() {}

    
    public GraphicsCard(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Integer memorySize) {
        this.memorySize = memorySize;
    }

    public String getMemoryBus() {
        return memoryBus;
    }

    public void setMemoryBus(String memoryBus) {
        this.memoryBus = memoryBus;
    }

    public Boolean getAdditionalPower() {
        return additionalPower;
    }

    public void setAdditionalPower(Boolean additionalPower) {
        this.additionalPower = additionalPower;
    }

    public String getPcieEx() {
        return pcieEx;
    }

    public void setPcieEx(String pcieEx) {
        this.pcieEx = pcieEx;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
