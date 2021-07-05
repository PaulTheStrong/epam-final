package test.epam.web.validator;

import com.epam.web.validator.BookValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookValidatorTest {

    public static final String OK = "OK";

    @Test
    public void testValidateTitleShouldReturnOKWhenInputIsValid() {
        BookValidator bookValidator = new BookValidator();
        String title = "validTitle";
        String actual = bookValidator.validateTitle(title);
        Assert.assertEquals(OK, actual);
    }

    @Test
    public void testValidateTitleShouldntReturnOKWhenInputIsEmptyString() {
        BookValidator bookValidator = new BookValidator();
        String title = "";
        String actual = bookValidator.validateTitle(title);
        Assert.assertNotEquals(OK, actual);
    }


    @Test
    public void testValidateTitleShouldntReturnOKWhenInputTooLong() {
        BookValidator bookValidator = new BookValidator();
        String title  = IntStream.range(0, 256).mapToObj(index -> "" + 'a').collect(Collectors.joining());
        String actual = bookValidator.validateTitle(title);
        Assert.assertNotEquals(OK, actual);
    }

    @Test
    public void testValidateDescriptionShouldReturnOKWhenInputIsValid() {
        BookValidator bookValidator = new BookValidator();
        String description = "valid description";
        String actual = bookValidator.validateDescription(description);
        Assert.assertEquals(OK, actual);
    }

    @Test
    public void testValidateDescriptionShouldReturnOKWhenInputIsEmptyString() {
        BookValidator bookValidator = new BookValidator();
        String description = "";
        String actual = bookValidator.validateDescription(description);
        Assert.assertEquals(OK, actual);
    }

    @Test
    public void testValidateDescriptionShouldntReturnOKWhenInputTooLong() {
        BookValidator bookValidator = new BookValidator();
        String description  = IntStream.range(0, 1025).mapToObj(index -> "a").collect(Collectors.joining());
        String actual = bookValidator.validateDescription(description);
        Assert.assertNotEquals(OK, actual);
    }

    @Test
    public void testValidateQuantityShouldReturnOKWhenInputIsValidString() {
        BookValidator bookValidator = new BookValidator();
        String quantity = "1234";
        String actual = bookValidator.validateQuantity(quantity);
        Assert.assertEquals(OK, actual);
    }

    @Test
    public void testValidateQuantityShouldntReturnOKWhenInputIsTooBig() {
        BookValidator bookValidator = new BookValidator();
        String quantity = "12340000000000000";
        String actual = bookValidator.validateQuantity(quantity);
        Assert.assertNotEquals(OK, actual);
    }

    @Test
    public void testValidateQuantityShouldntReturnOKWhenInputIsEmpty() {
        BookValidator bookValidator = new BookValidator();
        String quantity = "";
        String actual = bookValidator.validateQuantity(quantity);
        Assert.assertNotEquals(OK, actual);
    }

    @Test
    public void testValidateQuantityShouldntReturnOKWhenInputIsNotANumber() {
        BookValidator bookValidator = new BookValidator();
        String quantity = "asgs32341";
        String actual = bookValidator.validateQuantity(quantity);
        Assert.assertNotEquals(OK, actual);
    }

}
