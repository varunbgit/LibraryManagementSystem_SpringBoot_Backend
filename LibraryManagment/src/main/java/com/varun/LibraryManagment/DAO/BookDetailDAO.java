package com.varun.LibraryManagment.DAO;

import com.varun.LibraryManagment.entity.BookDetail;
import com.varun.LibraryManagment.entity.BookQuantity;

import java.util.List;

public interface BookDetailDAO {
    public List<BookDetail> findAll() throws Exception;

    BookDetail findBookById(Long id);
    public String deleteBookById(Long id) throws Exception;

    BookQuantity getQuantity(long id);

    boolean addBook(BookDetail bookDetail);

    boolean addBookQuantity(BookQuantity bookQuantity);

    BookQuantity updateBookQuant(BookQuantity bookQuant);
}
