package com.varun.LibraryManagment.service;

import com.varun.LibraryManagment.DAO.BookDetailDAOImpl;
import com.varun.LibraryManagment.entity.BookDetail;
import com.varun.LibraryManagment.entity.BookQuantity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Component
@Service
public class BookService {
    private BookDetailDAOImpl bookDetailDAOImpl;
    @Autowired
    BookService(BookDetailDAOImpl bookDAO){
        bookDetailDAOImpl = bookDAO;
    }

    public List<BookDetail> allBooks() throws Exception {
            return bookDetailDAOImpl.findAll();

    }

    public BookDetail getBook(long id) throws  RuntimeException{
         BookDetail book = bookDetailDAOImpl.findBookById(id);
         if(book == null) {
             throw new RuntimeException("No book With the given id found");
         }
         return book;
    }

    public List<BookQuantity> allQuant() {
        return bookDetailDAOImpl.findQuant();
    }

    @Transactional
    public String deleteBookById(Long id) throws Exception {
        try{
            return bookDetailDAOImpl.deleteBookById(id);
        }catch (SQLException e){
            throw new SQLException(e);
        }
        catch(Exception e){
            throw new Exception(e);
        }
    }

    public BookQuantity getQuantity(long id) {
        try{
            return bookDetailDAOImpl.getQuantity(id);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public boolean addBookDetail(BookDetail bookDetail) throws RuntimeException {
        try{
            return bookDetailDAOImpl.addBook(bookDetail);

        }catch (RuntimeException e){
            throw e;
        }
    }

    @Transactional
    public boolean addBookQuantity(BookQuantity bookQuantity) {
        try{
            boolean bookQuantadded = false;
            bookQuantadded = bookDetailDAOImpl.addBookQuantity(bookQuantity);
            return bookQuantadded;
        }catch (RuntimeException e){
            throw e;
        }
    }

    @Transactional
    public BookQuantity updateBook(BookQuantity bookQuant) {
        try{
            //this line throws exception if book not found
            BookQuantity book = bookDetailDAOImpl.getQuantity(bookQuant.getBookId());

            //if book found now this line works
            return bookDetailDAOImpl.updateBookQuant(bookQuant);
        }catch (RuntimeException ex){
            throw ex;
        }
    }
}
