package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class DateOfBirthTest {

    @Test
    public void constructor_validDate_createsDateOfBirth() {
        DateOfBirth dob = new DateOfBirth("01/01/2001");
        assertNotNull(dob);
        assertEquals("01/01/2001", dob.toString());
    }

    @Test
    public void isValidDate_validFormat_returnsTrue() {
        assertTrue(DateOfBirth.isValidDate("01/01/2001"));
    }

    @Test
    public void isValidDate_invalidFormat_returnsFalse() {
        assertFalse(DateOfBirth.isValidDate("01-01-2001"));
        assertFalse(DateOfBirth.isValidDate("2001/01/01"));
        assertFalse(DateOfBirth.isValidDate("01/01"));
        assertFalse(DateOfBirth.isValidDate("01/01/2001/01"));
    }

    @Test
    public void isValidDate_invalidLogicalDate_returnsFalse() {
        assertFalse(DateOfBirth.isValidDate("30/02/2025"));
        assertFalse(DateOfBirth.isValidDate("31/04/2025"));
    }

    @Test
    public void constructor_nullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            new DateOfBirth(null);
        });
    }

    @Test
    public void constructor_emptyThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DateOfBirth("");
        });
    }

    @Test
    public void equals_sameDateOfBirth_returnsTrue() {
        DateOfBirth dob1 = new DateOfBirth("01/01/2001");
        DateOfBirth dob2 = new DateOfBirth("01/01/2001");
        assertEquals(dob1, dob2);
    }

    @Test
    public void equals_differentDateOfBirth_returnsFalse() {
        DateOfBirth dob1 = new DateOfBirth("01/01/2001");
        DateOfBirth dob2 = new DateOfBirth("02/01/2001");
        assertNotEquals(dob1, dob2);
    }

    @Test
    public void toString_returnsCorrectFormat() {
        DateOfBirth dob = new DateOfBirth("31/12/2025");
        assertEquals("31/12/2025", dob.toString());
    }
}
