package com.varun.LibraryManagment.ServiceTest.BookService;

import com.varun.LibraryManagment.DAO.BookDetailDAOImpl;
import com.varun.LibraryManagment.entity.BookDetail;
import com.varun.LibraryManagment.entity.BookQuantity;
import com.varun.LibraryManagment.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookDetailDAOImpl bookDetailDAOImpl;
    @InjectMocks
    private BookService bookService;

    @Test
    void context(){
        assertThat(bookDetailDAOImpl).isNotNull();
        assertThat(bookService).isNotNull();
    }

    @Test
    void allBooks() throws Exception {
        when(bookDetailDAOImpl.findAll()).thenReturn(List.of(new BookDetail(1L,"Atomic"),new BookDetail(2L,"Habits")));
        Assertions.assertEquals(2,bookService.allBooks().size());
    }
    @Test
    void allBooks_case1() throws Exception {
        when(bookDetailDAOImpl.findAll()).thenReturn(List.of());
        Assertions.assertEquals(0,bookService.allBooks().size());
    }
    @Test
    void exceptionTest_AllBooks() throws Exception {
        String errorMessage = "Error saving the book";
        when(bookDetailDAOImpl.findAll()).thenThrow(new RuntimeException(errorMessage));
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> bookService.allBooks());
        assertTrue(exception.getMessage().contains(errorMessage));
    }

    @Test
    void getBook() {
        BookDetail expectedBook = new BookDetail(1L,"Atomic");
//        when(bookService.getBook(1L)).thenReturn(expectedBook);
        when(bookDetailDAOImpl.findBookById(any())).thenReturn(expectedBook);
        BookDetail actualbook = bookService.getBook(1);
        assertNotNull(actualbook);
        assertEquals(1L, actualbook.getBookId());
    }
    //book don't exist

    @Test
    void allQuant() {
        when(bookDetailDAOImpl.findQuant()).thenReturn(List.of(new BookQuantity(1L,10,10),new BookQuantity(2L,10,10)));
        assertThat(bookService.allQuant()).isNotNull();
        assertThat(bookService.allQuant().size()).isEqualTo(2);
    }

    // TODO Make a soft Delete
    @Test
    void deleteBookById() throws Exception {
        when(bookService.deleteBookById(1L)).thenReturn("True");
        assertEquals("True", bookService.deleteBookById(1L));
    }

    @Test
    void getQuantity() {
        when(bookService.getQuantity(1L)).thenReturn(new BookQuantity(1L, 10, 10));
        assertEquals(1L, bookService.getQuantity(1L).getBookId());
    }

    @Test
    void addBookDetail() {
        BookDetail book = new BookDetail(4L,"My Book");
        when(bookService.addBookDetail(book)).thenReturn(Boolean.TRUE);
        assertEquals(Boolean.TRUE,bookService.addBookDetail(book));

    }

    @Test
    void addBookQuantity() {
        BookQuantity book = new BookQuantity(2, 10,10);
        when(bookService.addBookQuantity(book)).thenReturn(Boolean.TRUE);
        assertThat(bookService.addBookQuantity(book)).isEqualTo(Boolean.TRUE);
    }

    @Test
    void updateBook() {
        BookQuantity book = new BookQuantity(2, 10,10);
        when(bookService.updateBook(book)).thenReturn(book);
        assertThat(bookService.updateBook(book)).isEqualTo(book);
    }
    @Test
    void invalidIID_updateBook(){

        BookQuantity noBook = new BookQuantity(3,1,10);
        when(bookService.updateBook(noBook)).thenThrow(new RuntimeException("Incorrect Id. Please Retry with correct Id"));
        RuntimeException ex  = assertThrows(RuntimeException.class, ()-> bookService.updateBook(noBook));
        assertTrue(ex.getMessage().contains("Incorrect Id. Please Retry with correct Id"));
    }
}