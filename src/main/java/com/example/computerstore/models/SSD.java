package com.example.computerstore.models;

import javax.persistence.*;

@Entity
@Table(name = "ssds")
public class SSD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private Integer capacity;
    private Integer readSpeed;
    private Integer writeSpeed;
    private String formFactor;
    private String interfaceType;
    private String memoryType;
    private Double price;
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

   
     public SSD() {}

    
     public SSD(Integer id) {
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getReadSpeed() {
        return readSpeed;
    }

    public void setReadSpeed(Integer readSpeed) {
        this.readSpeed = readSpeed;
    }

    public Integer getWriteSpeed() {
        return writeSpeed;
    }

    public void setWriteSpeed(Integer writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
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
