package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dto.BookDto;
import com.epam.web.enitity.Author;
import com.epam.web.enitity.Book;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.BookService;
import com.epam.web.validator.BookValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class EditBookCommand implements Command {

    private static final String TITLE_ERROR = "titleError";
    private static final String QUANTITY_ERROR = "quantityError";
    private static final String DESCRIPTION_ERROR = "descriptionError";
    private static final String AUTHOR_ERROR = "authorError";
    private static final String OK = "OK";
    private final BookValidator validator;

    public EditBookCommand (BookValidator validator) {
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

        String bookIdString = Optional.ofNullable(request.getParameter("bookId")).orElse("0");
        long bookId = Long.parseLong(bookIdString);
        if (request.getMethod().equals("POST")) {
            try {
                post(request, daoHelperFactory, bookId);
            } catch (ServletException | IOException e) {
                throw new ServiceException(e);
            }
        } else if (request.getMethod().equals("GET")) {
            return get(request, daoHelperFactory, bookId);
        }
        return CommandResult.redirect(request.getContextPath() + "/controller?command=editBook&bookId=" + bookId);
    }

    private CommandResult get(HttpServletRequest request, DaoHelperFactory daoHelperFactory, long bookId) throws ServiceException {
        BookService bookService = new BookService(daoHelperFactory);
        Optional<BookDto> bookOptional = bookService.getById(bookId);

        putErrors(request);

        if (bookOptional.isPresent()) {
            request.setAttribute("book", bookOptional.get());
        } else if (bookId == 0) {
            Book newBook = new Book(0, "Title", "Description", "", 0);
            bookService.save(newBook, Collections.emptyList(), Collections.emptyList());
            long lastId = bookService.getLastId();
            return CommandResult.redirect(request.getContextPath() + "/controller?command=editBook&bookId=" + lastId);
        } else {
            request.setAttribute("bookNotFoundError", "error.bookNotFound");
        }
        return CommandResult.forward("WEB-INF/pages/edit-book.jsp");
    }

    private void post(HttpServletRequest request, DaoHelperFactory daoHelperFactory, long bookId) throws ServiceException, IOException, ServletException {
        boolean isOk = true;
        HttpSession session = request.getSession();
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String quantityParameter = request.getParameter("quantity");
        int quantity = 0;

        String titleStatus = validator.validateTitle(title);
        if (!titleStatus.equals(OK)) {
            session.setAttribute(TITLE_ERROR, titleStatus);
            isOk = false;
        }

        String descriptionStatus = validator.validateDescription(description);
        if (!descriptionStatus.equals(OK)) {
            session.setAttribute(DESCRIPTION_ERROR, descriptionStatus);
            isOk = false;
        }

        String quantityStatus = validator.validateQuantity(quantityParameter);
        if (!quantityStatus.equals(OK)) {
            session.setAttribute(QUANTITY_ERROR, descriptionStatus);
            isOk = false;
        } else {
            quantity = Integer.parseInt(quantityParameter);
        }

        String[] emptyArray = {};
        String[] names = Optional.ofNullable(request.getParameterValues("name")).orElse(emptyArray);
        String[] surnames = Optional.ofNullable(request.getParameterValues("surname")).orElse(emptyArray);

        List<Author> authors = IntStream.range(0, names.length)
                .mapToObj(i -> new Author(0, names[i], surnames[i]))
                .filter(author -> validator.validateAuthor(author).equals(OK))
                .collect(Collectors.toList());

        String[] genresArray = Optional.ofNullable(request.getParameterValues("genre")).orElse(emptyArray);
        List<String> genres = Arrays.stream(genresArray)
                .map(String::trim)
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());

        Part part = request.getPart("image");
        String previousPath = request.getParameter("previousPath");
        Book book = new Book(bookId, title, description, previousPath, quantity);

        String uploadedName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        if (!uploadedName.isEmpty()) {
            updateImage(request, bookId, part, book, uploadedName);
        }

        BookService bookService = new BookService(daoHelperFactory);
        if (isOk) {
            bookService.save(book, authors, genres);
        }
    }

    private void updateImage(HttpServletRequest request, long bookId, Part part, Book book, String uploadedName) throws IOException {
        String extension = uploadedName.substring(uploadedName.lastIndexOf("."));

        ServletContext servletContext = request.getServletContext();

        String databaseImageBase = servletContext.getInitParameter("DATABASE_IMAGE_BASE");
        String databasePath = databaseImageBase + bookId + extension;
        book.setImagePath(databasePath);

        String uploadDirectory = servletContext.getInitParameter("IMAGE_UPLOAD_PATH");
        String savePath = uploadDirectory + bookId + extension;
        writePart(part, savePath);
    }

    private void writePart(Part part, String filename) throws IOException {
        InputStream in = part.getInputStream();
        FileOutputStream out = new FileOutputStream(filename);
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        while (len != -1) {
            out.write(buffer, 0, len);
            len = in.read(buffer);
        }
    }

    private void putErrors(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String titleError = (String) session.getAttribute(TITLE_ERROR);
        if (titleError != null) {
            request.setAttribute(TITLE_ERROR, titleError);
            session.removeAttribute(TITLE_ERROR);
        }
        String quantityError = (String) session.getAttribute(QUANTITY_ERROR);
        if (quantityError != null) {
            request.setAttribute(QUANTITY_ERROR, quantityError);
            session.removeAttribute(QUANTITY_ERROR);
        }
        String descriptionError = (String) session.getAttribute(DESCRIPTION_ERROR);
        if (descriptionError != null) {
            request.setAttribute(DESCRIPTION_ERROR, descriptionError);
            session.removeAttribute(DESCRIPTION_ERROR);
        }
        String authorError = (String) session.getAttribute(AUTHOR_ERROR);
        if (authorError != null) {
            request.setAttribute(AUTHOR_ERROR, authorError);
            session.removeAttribute(AUTHOR_ERROR);
        }
    }
}
