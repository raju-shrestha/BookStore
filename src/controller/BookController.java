package controller;

import domain.Book;
import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BookController")
public class BookController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //if no action parameter is passed, then default will be "list"
        String action = request.getParameter("action") == null ? "list" : request.getParameter("action");
        System.out.println("action = " + action);

        // display book list page
        if (action.equalsIgnoreCase("list")) {
            List<Book> bookList = new BookService().getBookList();
            //query execution end
            request.setAttribute("booklist", bookList); // add the list obtained from DB to request to sent to jsp page
            RequestDispatcher rd = request.getRequestDispatcher("/pages/book.jsp"); // this holds table to show the list of books
            rd.forward(request, response);
        }
        // to display add Book form page
        else if (action.equalsIgnoreCase("add")) {
            response.sendRedirect("/pages/addBook.jsp");
            return; //to stop further code execution (optional)
        }
        // to add to db
        else if (action.equalsIgnoreCase("addNew")) { // for adding new book to db with values which we get from form(/pages/addBook.jsp)
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String category = request.getParameter("category");
            String isbn = request.getParameter("isbn");
            String price = request.getParameter("price");
            String purchased_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //get today's date in yyyy-MM-dd format
//creating new Book object via constructor and pass to BookService
            Boolean isSaved = new BookService().saveBook(new Book(name, author, isbn, price, purchased_date, category));

            if (isSaved) {
                System.out.println("saved");
                //message to user with either request or session or any othe method
                response.sendRedirect("/book"); // "/book" will again redirect to BookController as defined in web.xml with no action
            }
        }
        //updating particular book
        else if (action.equalsIgnoreCase("update")) {
            String idToUpdate = request.getParameter("id"); //get id to update (here id will be passed via url (link creation part))

            Book bookToUpdate = new BookService().getBookInfo(idToUpdate);
            request.setAttribute("bookInfo", bookToUpdate); // we will check for bookInfo in jsp page
            request.getRequestDispatcher("/pages/addBook.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("updateBook")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String category = request.getParameter("category");
            String isbn = request.getParameter("isbn");
            String price = request.getParameter("price");
            String purchased_date = request.getParameter("purchased_date");
            Boolean isUpdated = new BookService().updateRecord(new Book(id, name, author, isbn, price, purchased_date, category));

            if (isUpdated) {
                System.out.println("updated");
                //message to user with either request or session or any other method
                response.sendRedirect("/book"); // "/book" will again redirect to BookController as defined in web.xml with no action
            }
        } else if (action.equalsIgnoreCase("delete")) {
            String id = request.getParameter("id"); // will be passed via url from href
            Boolean isDeleted = new BookService().deleteBook(id);
            System.out.println("isDeleted = " + isDeleted);
            if (isDeleted) {
                System.out.println("deleted");
                //message to user with either request or session or any other method
                response.sendRedirect("/book"); // "/book" will again redirect to BookController as defined in web.xml with no action
            }
        }
    }
}
