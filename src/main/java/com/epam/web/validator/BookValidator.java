package com.epam.web.validator;

public class BookValidator {

    public static final String EDIT_ERROR_ZERO_LENGTH_TITLE = "edit.error.zeroLengthTitle";
    public static final String EDIT_ERROR_TITLE_TOO_LONG = "edit.error.titleTooLong";
    public static final String EDIT_ERROR_DESCRIPTION_TOO_LONG_DESCRIPTION = "edit.error.description.tooLongDescription";
    public static final String OK = "OK";
    public static final String EDIT_ERROR_QUANTITY_NOT_NUMBER = "edit.error.quantity.notNumber";
    public static final String EDIT_ERROR_QUANTITY_WRONG_BOUNDS = "edit.error.quantity.wrongBounds";

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

    public String validateAuthor(String name, String surname) {
        if (name.length() > 50 || surname.length() > 50) {
            return "edit.error.author.tooLong";
        }
        return OK;
    }

}
