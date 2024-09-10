package com.example.computerstore.models;

import javax.persistence.*;

@Entity
@Table(name = "power_supplies")
public class PowerSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private Integer wattage;
    private String protectionTechnologies;
    private String gpuConnectorType;
    private Double price;
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

   
     public PowerSupply() {}

    
     public PowerSupply(Integer id) {
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

    public Integer getWattage() {
        return wattage;
    }

    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }

    public String getProtectionTechnologies() {
        return protectionTechnologies;
    }

    public void setProtectionTechnologies(String protectionTechnologies) {
        this.protectionTechnologies = protectionTechnologies;
    }

    public String getGpuConnectorType() {
        return gpuConnectorType;
    }

    public void setGpuConnectorType(String gpuConnectorType) {
        this.gpuConnectorType = gpuConnectorType;
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
