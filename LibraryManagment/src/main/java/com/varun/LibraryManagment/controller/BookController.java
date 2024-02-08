package com.varun.LibraryManagment.controller;

import com.varun.LibraryManagment.entity.BookDetail;
import com.varun.LibraryManagment.entity.Book;
import com.varun.LibraryManagment.entity.BookQuantity;
import com.varun.LibraryManagment.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
//@Component
public class BookController {

//  FOR TESTING IF SERVER RUNS FINE
    @GetMapping("/hi")
    public String fun (){
        return  "HELLO! HOPE YOU ARE DOING WELL";
    }

    private BookService bookService;
    @Autowired
    BookController(BookService bookSer){
        bookService = bookSer;
    }

    //to get all the books available in library
    @GetMapping("/books")
    public  List<BookDetail> books() throws SQLException {
        try {
            return bookService.allBooks();
        }catch (SQLException e){
            throw (e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/quants")
    public  List<BookQuantity> booksquant(){
        try{
            return bookService.allQuant();
        }catch (Exception e){
            throw e;
        }
    }

    //to get the details of one book
    @GetMapping("/books/{book_id}")
    public BookDetail getBookDetail(@PathVariable long book_id){
        try{
            return bookService.getBook(book_id);
        }catch(RuntimeException e){
            throw e;
        }
    }

    //to get the current quantity of a given book
    @GetMapping("/quants/{book_id}")
    public BookQuantity getQuantity(@PathVariable long book_id) throws RuntimeException {
        try{
            return bookService.getQuantity(book_id);
        }catch(RuntimeException e){
            throw (e);
        }
    }

    // for adding new book
    //must add book in both the DB of books
    //provides books detail in request header
    @PostMapping("/books")
    public boolean addBook(@RequestBody Book book) throws SQLException {
        try{
            BookDetail bookDetail = book.getBookDetail();
            BookQuantity bookQuantity = book.getBookQuantity();
            boolean addedBookDetail = false;
            boolean addedBookQuantity = false;
            addedBookDetail = bookService.addBookDetail(bookDetail);
            addedBookQuantity = bookService.addBookQuantity(bookQuantity);
            return addedBookQuantity & addedBookDetail;
        }catch (RuntimeException e){
            throw e;
        }
    }

//    //for updating the book Quantity details
    @PutMapping("/books")
    public BookQuantity updateBookQuant(@RequestBody BookQuantity bookQuant){
        return bookService.updateBook(bookQuant);

    }
//
//    //deleting the book
//    //must delete book from both the mapping
    @DeleteMapping("/books/{book_id}")
    public String deleteById(@PathVariable long book_id) throws SQLException {
        try{
            return ("Sorry Deletion of Book Not allowed " + "Suggest you to set the quantity of the book to Zero");
            //it is not a got practice to delete in production server.
            //alternatively keep another column and set its status as Deleted
        }catch(Exception e){
            throw new SQLException(e);
        }
    }
}
