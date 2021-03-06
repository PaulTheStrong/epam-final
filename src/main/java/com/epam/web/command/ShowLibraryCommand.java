package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dto.BookDto;
import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.enitity.Genre;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ShowLibraryCommand implements Command {

    private static final String ORDERED_BOOK = "orderedBook";
    private static final String AUTHOR_ID = "authorId";
    private static final String GENRE_ID = "genreId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        BookService bookService = new BookService(new DaoHelperFactory());
        List<BookDto> books;
        String authorIdString = request.getParameter(AUTHOR_ID);
        String genreIdString = request.getParameter(GENRE_ID);

        HttpSession session = request.getSession();
        BookDto orderedBook = (BookDto) session.getAttribute(ORDERED_BOOK);
        if (orderedBook != null) {
            request.setAttribute(ORDERED_BOOK, orderedBook);
            session.removeAttribute(ORDERED_BOOK);
        }

        long page = Long.parseLong(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int elementsOnPage = 5;
        boolean isLast;

        if (authorIdString != null) {
            request.setAttribute(AUTHOR_ID, authorIdString);
            long authorId = Long.parseLong(authorIdString);
            books = bookService.getBooksByAuthorId(authorId, page - 1, elementsOnPage);
            isLast = bookService.countBooksByAuthorId(authorId) <= page * elementsOnPage;
        } else if (genreIdString != null) {
            request.setAttribute(GENRE_ID, genreIdString);
            long genreId = Long.parseLong(genreIdString);
            books =  bookService.getBooksByGenreId(genreId, page - 1, elementsOnPage);
            isLast = bookService.countBooksByGenreId(genreId) <= page * elementsOnPage;
        } else {
            books = bookService.getBooksOnPage(page - 1, elementsOnPage);
            isLast = bookService.countBooks() <= page * elementsOnPage;
        }

        List<Genre> genres = bookService.getAllGenresWhereBookAttached();
        List<Author> authors = bookService.getAllAuthorsWhereBookAttached();


        request.setAttribute("currentPage", page);
        request.setAttribute("isLast", isLast);

        request.setAttribute("bookList", books);
        request.setAttribute("genreList", genres);
        request.setAttribute("authorList", authors);

        return CommandResult.forward("WEB-INF/pages/library.jsp");

    }
}
