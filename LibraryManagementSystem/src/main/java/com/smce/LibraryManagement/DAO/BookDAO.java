package com.smce.LibraryManagement.DAO;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.smce.LibraryManagement.Model.Book;
import com.smce.LibraryManagement.Util.DBConnection;

public class BookDAO {
    private Connection connection;

    public BookDAO() {
        connection = DBConnection.getConnection();
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setAvailableCopies(resultSet.getInt("available_copies"));
            book.setTotalCopies(resultSet.getInt("total_copies"));
            books.add(book);
        }
        return books;
    }

    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO books (title, author, publisher, available_copies, total_copies) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setString(3, book.getPublisher());
        preparedStatement.setInt(4, book.getAvailableCopies());
        preparedStatement.setInt(5, book.getTotalCopies());
        preparedStatement.executeUpdate();
    }

    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE books SET title = ?, author = ?, publisher = ?, available_copies = ?, total_copies = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setString(3, book.getPublisher());
        preparedStatement.setInt(4, book.getAvailableCopies());
        preparedStatement.setInt(5, book.getTotalCopies());
        preparedStatement.setInt(6, book.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteBook(int bookId) throws SQLException {
        String query = "DELETE FROM books WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bookId);
        preparedStatement.executeUpdate();
    }
}
