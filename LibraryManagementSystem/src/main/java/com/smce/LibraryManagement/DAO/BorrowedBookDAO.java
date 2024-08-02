package com.smce.LibraryManagement.DAO;


import com.smce.LibraryManagement.Model.BorrowedBook;
import com.smce.LibraryManagement.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowedBookDAO {
    private Connection connection;

    public BorrowedBookDAO() {
        connection = DBConnection.getConnection();
    }

    public List<BorrowedBook> getAllBorrowedBooks() throws SQLException {
        List<BorrowedBook> borrowedBooks = new ArrayList<>();
        String query = "SELECT * FROM borrowed_books";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            BorrowedBook borrowedBook = new BorrowedBook();
            borrowedBook.setId(resultSet.getInt("id"));
            borrowedBook.setBookId(resultSet.getInt("book_id"));
            borrowedBook.setMemberId(resultSet.getInt("member_id"));
            borrowedBook.setBorrowDate(resultSet.getDate("borrow_date"));
            borrowedBook.setDueDate(resultSet.getDate("due_date"));
            borrowedBook.setReturnDate(resultSet.getDate("return_date"));
            borrowedBook.setFine(resultSet.getDouble("fine"));
            borrowedBooks.add(borrowedBook);
        }
        return borrowedBooks;
    }

    public void addBorrowedBook(BorrowedBook borrowedBook) throws SQLException {
        String query = "INSERT INTO borrowed_books (book_id, member_id, borrow_date, due_date, return_date, fine) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, borrowedBook.getBookId());
        preparedStatement.setInt(2, borrowedBook.getMemberId());
        preparedStatement.setDate(3, new Date(borrowedBook.getBorrowDate().getTime()));
        preparedStatement.setDate(4, new Date(borrowedBook.getDueDate().getTime()));
        preparedStatement.setDate(5, borrowedBook.getReturnDate() != null ? new Date(borrowedBook.getReturnDate().getTime()) : null);
        preparedStatement.setDouble(6, borrowedBook.getFine());
        preparedStatement.executeUpdate();
    }

    public void updateBorrowedBook(BorrowedBook borrowedBook) throws SQLException {
        String query = "UPDATE borrowed_books SET book_id = ?, member_id = ?, borrow_date = ?, due_date = ?, return_date = ?, fine = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, borrowedBook.getBookId());
        preparedStatement.setInt(2, borrowedBook.getMemberId());
        preparedStatement.setDate(3, new Date(borrowedBook.getBorrowDate().getTime()));
        preparedStatement.setDate(4, new Date(borrowedBook.getDueDate().getTime()));
        preparedStatement.setDate(5, borrowedBook.getReturnDate() != null ? new Date(borrowedBook.getReturnDate().getTime()) : null);
        preparedStatement.setDouble(6, borrowedBook.getFine());
        preparedStatement.setInt(7, borrowedBook.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteBorrowedBook(int borrowedBookId) throws SQLException {
        String query = "DELETE FROM borrowed_books WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, borrowedBookId);
        preparedStatement.executeUpdate();
    }
}
