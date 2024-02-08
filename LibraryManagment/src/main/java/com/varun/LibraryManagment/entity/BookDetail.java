package com.varun.LibraryManagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Struct;

@Entity
@Table(name = "book_detail")
@Getter
@Setter
//@AllArgsConstructor
public class BookDetail {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    Long bookId;
    @Column(name = "name")
    String bookName;

    public BookDetail(Long id, String bookName, long curr, long total) {
//        this.bookId = bookId;
        new BookQuantity(id, curr, total);  //create the entry in Book quantity table as well
        this.bookName = bookName;
    }

    public BookDetail() {

    }
    public BookDetail(Long id, String name){
        bookId = id;
        bookName = name;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "bookId=" + bookId +
                ", bookName=" + bookName +
                '}';
    }

}
