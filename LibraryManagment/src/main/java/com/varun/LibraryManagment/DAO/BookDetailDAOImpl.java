package com.varun.LibraryManagment.DAO;

import com.varun.LibraryManagment.entity.BookDetail;
import com.varun.LibraryManagment.entity.BookQuantity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public class BookDetailDAOImpl implements BookDetailDAO {

    private EntityManager entityManager;

    @Autowired
    BookDetailDAOImpl(EntityManager en) {
        entityManager = en;
    }

    public String deleteBookById(Long id) throws SQLException {

        try {
            BookDetail book = entityManager.find(BookDetail.class, id);
            BookQuantity bookQuant = entityManager.find(BookQuantity.class, id);

            entityManager.remove(book);
            entityManager.remove(bookQuant);
            return "Book Deleted Successfully";
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<BookDetail> findAll() throws Exception {
        try {
            TypedQuery<BookDetail> query = entityManager.createQuery("from BookDetail ", BookDetail.class);
            List<BookDetail> books = query.getResultList();
            return books;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookQuantity> findQuant() {
        TypedQuery<BookQuantity> query = entityManager.createQuery("from BookQuantity ", BookQuantity.class);
        return query.getResultList();
    }

    @Override
    public BookDetail findBookById(Long id) {
        BookDetail book = entityManager.find(BookDetail.class, id);
        return book;
    }

    @Override
    public BookQuantity getQuantity(long id) {
        BookQuantity book = entityManager.find(BookQuantity.class, id);
        if (book == null) {
            throw new RuntimeException("Incorrect Id. Please Retry with correct Id");
        }
//        String response = "Book id: " + id + " current Quantity: " + book.getCurrentQuantity() + " total Quantity: " + book.getTotalQuantity();
//        return response;
        return book;
    }

    @Override
    public boolean addBook(BookDetail bookDetail) {
        try {
            entityManager.persist(bookDetail);
            return true;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public boolean addBookQuantity(BookQuantity bookQuantity) {
        try{
            entityManager.persist(bookQuantity);
            return true;
        }catch (Exception e){
            throw e;
        }
  }

    @Override
    public BookQuantity updateBookQuant(BookQuantity bookQuant) {
        try{
            //book is present this is checked in the service layer logic
            //now just update the bookQuantity
            return entityManager.merge(bookQuant);
        }catch (RuntimeException e){
            throw e;

        }

    }
}