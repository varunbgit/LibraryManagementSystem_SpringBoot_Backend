package com.varun.LibraryManagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book_quantity")
@Getter
@Setter
@AllArgsConstructor
public class BookQuantity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    long bookId;
    @Column(name =  "current_quantity")
    long currentQuantity;
    @Column(name = "total_quantity")
    long totalQuantity;

    public BookQuantity() {}

}
