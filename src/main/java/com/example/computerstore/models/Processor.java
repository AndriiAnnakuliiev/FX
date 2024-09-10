package com.example.computerstore.models;

import javax.persistence.*;

@Entity
@Table(name = "processors")
public class Processor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String socket;
    private Integer coreCount;
    private Integer threadCount;
    private Double baseClock;
    private Double boostClock;
    private Integer l1Cache;
    private Integer l2Cache;
    private Integer l3Cache;
    private Double price;
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


   
     public Processor() {}

     
     public Processor(Integer id) {
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

    public Integer getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(Integer coreCount) {
        this.coreCount = coreCount;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public Double getBaseClock() {
        return baseClock;
    }

    public void setBaseClock(Double baseClock) {
        this.baseClock = baseClock;
    }

    public Double getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(Double boostClock) {
        this.boostClock = boostClock;
    }

    public Integer getL1Cache() {
        return l1Cache;
    }

    public void setL1Cache(Integer l1Cache) {
        this.l1Cache = l1Cache;
    }

    public Integer getL2Cache() {
        return l2Cache;
    }

    public void setL2Cache(Integer l2Cache) {
        this.l2Cache = l2Cache;
    }

    public Integer getL3Cache() {
        return l3Cache;
    }

    public void setL3Cache(Integer l3Cache) {
        this.l3Cache = l3Cache;
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
