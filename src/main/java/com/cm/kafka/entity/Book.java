package com.cm.kafka.entity;

import lombok.Data;

/**
 * @author cm.g
 * @date 2020/9/11 14:06
 * @Description:
 */
@Data
public class Book {
    private Long id;
    private String name;

    public Book() {
    }

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
