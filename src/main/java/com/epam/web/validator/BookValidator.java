package com.epam.web.validator;

import com.epam.web.enitity.Author;

public class BookValidator {

    private static final String OK = "OK";
    private static final String EDIT_ERROR_ZERO_LENGTH_TITLE = "edit.error.zeroLengthTitle";
    private static final String EDIT_ERROR_TITLE_TOO_LONG = "edit.error.titleTooLong";
    private static final String EDIT_ERROR_DESCRIPTION_TOO_LONG_DESCRIPTION = "edit.error.description.tooLongDescription";
    private static final String EDIT_ERROR_QUANTITY_NOT_NUMBER = "edit.error.quantity.notNumber";
    private static final String EDIT_ERROR_QUANTITY_WRONG_BOUNDS = "edit.error.quantity.wrongBounds";
    private static final String EDIT_ERROR_AUTHOR_TOO_LONG = "edit.error.author.tooLong";
    private static final String EDIT_ERROR_AUTHOR_EMPTY_STRING = "edit.error.author.emptyString";

    public String validateTitle(String title) {
        if (title.isEmpty()) {
            return EDIT_ERROR_ZERO_LENGTH_TITLE;
        } else if (title.length() > 255) {
            return EDIT_ERROR_TITLE_TOO_LONG;
        }
        else return OK;
    }

    public String validateDescription(String description) {
        if (description.length() > 1024) {
            return EDIT_ERROR_DESCRIPTION_TOO_LONG_DESCRIPTION;
        }
        return OK;
    }

    public String validateQuantity(String quantityString) {
        try {
            int quantity = Integer.parseInt(quantityString);
            if (quantity < 0) {
                return EDIT_ERROR_QUANTITY_WRONG_BOUNDS;
            }
        } catch (NumberFormatException e) {
            return EDIT_ERROR_QUANTITY_NOT_NUMBER;
        }

        return OK;
    }

    public String validateAuthor(Author author) {
        if (author.getName().length() > 50 || author.getSurname().length() > 50) {
            return EDIT_ERROR_AUTHOR_TOO_LONG;
        }
        if (author.getName().isEmpty() || author.getSurname().isEmpty()) {
            return EDIT_ERROR_AUTHOR_EMPTY_STRING;
        }
        return OK;
    }

}
