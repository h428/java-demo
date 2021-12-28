package com.lab.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Item implements Serializable {
    @Id
    private Long id;

    private String name;

    private Float price;

    private String note;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Item(Long id, String name, Float price, String note, Integer status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.note = note;
        this.status = status;
    }

    public Item() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}