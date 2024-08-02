package com.smce.LibraryManagement.Services;



import java.sql.SQLException;
import java.util.List;

import com.smce.LibraryManagement.DAO.BookDAO;
import com.smce.LibraryManagement.Model.Book;

public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        bookDAO = new BookDAO();
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) throws SQLException {
        bookDAO.updateBook(book);
    }

    public void deleteBook(int bookId) throws SQLException {
        bookDAO.deleteBook(bookId);
    }
}

