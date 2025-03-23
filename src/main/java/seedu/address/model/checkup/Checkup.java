package seedu.address.model.checkup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * test
 */
public class Checkup {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String MESSAGE_INVALID_DATETIME = "Time slot is not available";
    public static final String MESSAGE_OUTSIDE_BUSINESS_HOURS =
            "Checkup must be scheduled between 9:00 AM and 5:00 PM";
    public static final String MESSAGE_PAST_DATE =
            "Checkup cannot be scheduled in the past";
    private static final LocalTime START_TIME = LocalTime.of(9, 0);
    private static final LocalTime END_TIME = LocalTime.of(17, 0);
    public final LocalDateTime checkupDateTime;


    /**
     * test
     * @param checkupDate test
     * @param checkupTime test
     * @throws ParseException test
     */
    public Checkup(LocalDate checkupDate, LocalTime checkupTime) throws ParseException {
        allNonNull(checkupDate, checkupTime);
        checkArgument(isValidCheckup(checkupDate, checkupTime), MESSAGE_CONSTRAINTS);
        this.checkupDateTime = createCheckupDateTime(checkupDate, checkupTime);
    }

    /**
     * test
     * @param checkupDate test
     * @param checkupTime test
     */
    public void allNonNull(LocalDate checkupDate, LocalTime checkupTime) {
        requireNonNull(checkupDate);
        requireNonNull(checkupTime);
    }

    /**
     * Returns true if a given string is a valid checkUpDate name.
     */
    public static boolean isValidCheckup(LocalDate checkupDate, LocalTime checkupTime)
            throws ParseException {
        LocalDateTime checkupDateTime = createCheckupDateTime(checkupDate, checkupTime);
        if (!isWithinBusinessHours(checkupDateTime)) {
            throw new ParseException(MESSAGE_OUTSIDE_BUSINESS_HOURS);
        }
        if (!isNotInPast(checkupDateTime)) {
            throw new ParseException(MESSAGE_PAST_DATE);
        }
        return true;
    }

    private static LocalDateTime createCheckupDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }
    private static boolean isWithinBusinessHours(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();
        return !time.isBefore(START_TIME) && !time.isAfter(END_TIME);
    }

    private static boolean isNotInPast(LocalDateTime dateTime) {
        return !dateTime.isBefore(LocalDateTime.now());
    }

    public LocalDateTime getDateTime() {
        return this.checkupDateTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Checkup otherCheckup)) {
            return false;
        }

        return checkupDateTime.equals(otherCheckup.checkupDateTime);
    }

    public LocalDate getCheckupDate() {
        return this.checkupDateTime.toLocalDate();
    }

    public LocalTime getCheckupTime() {
        return this.checkupDateTime.toLocalTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkupDateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return checkupDateTime.format(formatter);
    }
}
