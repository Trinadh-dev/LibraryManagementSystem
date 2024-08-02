package com.smce.LibraryManagement.Services;



import java.sql.SQLException;
import java.util.List;

import com.smce.LibraryManagement.DAO.BorrowedBookDAO;
import com.smce.LibraryManagement.Model.BorrowedBook;

public class BorrowedBookService {
    private BorrowedBookDAO borrowedBookDAO;

    public BorrowedBookService() {
        borrowedBookDAO = new BorrowedBookDAO();
    }

    public List<BorrowedBook> getAllBorrowedBooks() throws SQLException {
        return borrowedBookDAO.getAllBorrowedBooks();
    }

    public void addBorrowedBook(BorrowedBook borrowedBook) throws SQLException {
        borrowedBookDAO.addBorrowedBook(borrowedBook);
    }

    public void updateBorrowedBook(BorrowedBook borrowedBook) throws SQLException {
        borrowedBookDAO.updateBorrowedBook(borrowedBook);
    }

    public void deleteBorrowedBook(int borrowedBookId) throws SQLException {
        borrowedBookDAO.deleteBorrowedBook(borrowedBookId);
    }
}

