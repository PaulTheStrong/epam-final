package com.epam.web.commands;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.Genre;
import com.epam.web.exceptions.ServiceException;
import com.epam.web.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowLibraryCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        BookService bookService = new BookService(new DaoHelperFactory());
        List<Book> books;
        String author_id = request.getParameter("author_id");
        String genre_id = request.getParameter("genre_id");

        if (author_id != null) {
            books = bookService.getBooksByAuthorId(Long.parseLong(author_id));
        } else if (genre_id != null) {
            books =  bookService.getBooksByGenreId(Long.parseLong(genre_id));
        } else {
            books = bookService.getAllBooks();
        }
        List<Genre> genres = bookService.getAllGenres();
        List<Author> authors = bookService.getAllAuthors();

        request.setAttribute("bookList", books);
        request.setAttribute("genreList", genres);
        request.setAttribute("authorList", authors);

        return CommandResult.forward("WEB-INF/pages/library.jsp");

    }
}
