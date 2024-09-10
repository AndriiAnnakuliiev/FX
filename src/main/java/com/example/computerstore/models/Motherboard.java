package com.example.computerstore.models;

import javax.persistence.*;

@Entity
@Table(name = "motherboards")
public class Motherboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String socket;
    private String ramType;
    private String pcieEx;
    private String formFactor;
    private String chipset;
    private Boolean integratedGraphics;
    private Double price;
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

     
     public Motherboard() {}

     
     public Motherboard(Integer id) {
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

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String getPcieEx() {
        return pcieEx;
    }

    public void setPcieEx(String pcieEx) {
        this.pcieEx = pcieEx;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Boolean getIntegratedGraphics() {
        return integratedGraphics;
    }

    public void setIntegratedGraphics(Boolean integratedGraphics) {
        this.integratedGraphics = integratedGraphics;
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
