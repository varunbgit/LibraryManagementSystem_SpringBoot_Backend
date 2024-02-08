package com.varun.LibraryManagment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    long id;
    String name;
    int current_quantity;
    int total_quantity;

    public BookQuantity getBookQuantity(){
        return new BookQuantity(id,current_quantity,total_quantity);
    }
    public BookDetail getBookDetail(){
        return new BookDetail(id,name);
    }
}
