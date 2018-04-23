package service;

import dbUtils.DBConnection;
import domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This service will handle the necessary business logic requried for handling book domain
 */
public class BookService {
    private String table_name = "book";
    Connection connection = null;

    public BookService() {
        connection = new DBConnection().getConnection();
    }

    /**
     * List all books from DB
     *
     * @return list of books
     */
    public List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();

        String query = "SELECT * FROM " + table_name; //query
        PreparedStatement statement = null;
        //create preparedStatement
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //run the preparedstatement query
        try {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                Book book = new Book(); //create new book object in each loop and add that object to list
                book.setId(rs.getString("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setIsbn(rs.getString("isbn"));
                book.setPrice(rs.getString("price"));
                book.setPurchased_date(rs.getString("purchased_date"));

                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection); //finally close the connection to db
        }

        return bookList;
    }

    /**
     * Save to database
     */
    public boolean saveBook(Book book) {
        String query = "INSERT INTO " + table_name + " (name , author, category, isbn, price, purchased_date) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, book.getName());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getCategory());
            pst.setString(4, book.getIsbn());
            pst.setDouble(5, Double.parseDouble(book.getPrice()));
            pst.setString(6, book.getPurchased_date());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }
        System.out.println("query = " + query);
        return false;
    }

    /**
     * Get Info to Update particular book
     */

    public Book getBookInfo(String id) {
        try {
            Book bookInfo = new Book(); //List is not used here as we will get only one record with given id
            String query = "SELECT * FROM " + table_name + " WHERE id=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                bookInfo.setId(rs.getString("id"));
                bookInfo.setName(rs.getString("name"));
                bookInfo.setAuthor(rs.getString("author"));
                bookInfo.setIsbn(rs.getString("isbn"));
                bookInfo.setCategory(rs.getString("category"));
                bookInfo.setPrice(rs.getString("price"));
                bookInfo.setPurchased_date(rs.getString("purchased_date"));
            }
            return bookInfo;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }

        return null;
    }

    /**
     * Updating the existing record in db
     */
    public boolean updateRecord(Book book) {
        try {
            String query = "UPDATE " + table_name + " SET name =?, author =?, isbn=?, category=?, price=?, purchased_date =? WHERE id=?;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst = connection.prepareStatement(query);
            pst.setString(1, book.getName());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getIsbn());
            pst.setString(4, book.getCategory());
            pst.setDouble(5, Double.parseDouble(book.getPrice()));
            pst.setString(6, book.getPurchased_date());
            pst.setString(7, book.getId());
            return pst.executeUpdate() != 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }

        return false;
    }

    /**
     * Delete record from db
     *
     * @param id
     * @return
     */
    public boolean deleteBook(String id) {
        try {
            String query = "DELETE FROM " + table_name + " WHERE id =?;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, id);
            return pst.executeUpdate() != 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }

        return false;
    }
}
