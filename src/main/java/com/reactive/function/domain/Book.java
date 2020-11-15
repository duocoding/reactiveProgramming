package com.reactive.function.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName Book
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/11/15 11:01
 * @Version 1.0
 */

@NoArgsConstructor
//@AllArgsConstructor
@Data
public class Book {

    private String title;
    private BigDecimal price;
    private String catagory;

    public Book(String title, BigDecimal price, String catagory) {
        this.title = title;
        this.price = price;
        this.catagory = catagory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
}
