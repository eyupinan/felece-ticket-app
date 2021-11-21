package org.felecechallenge.ticket.facade.dto.route;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class UserRouteData {
    private Long id;
    private String startDestinationName;
    private String endDestinationName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+03:00")
    private Date date;
    private BigDecimal price;
    private Boolean isFull;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getFull() {
        return isFull;
    }

    public void setFull(Boolean full) {
        isFull = full;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStartDestinationName() {
        return startDestinationName;
    }

    public void setStartDestinationName(String startDestinationName) {
        this.startDestinationName = startDestinationName;
    }

    public String getEndDestinationName() {
        return endDestinationName;
    }

    public void setEndDestinationName(String endDestinationName) {
        this.endDestinationName = endDestinationName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
