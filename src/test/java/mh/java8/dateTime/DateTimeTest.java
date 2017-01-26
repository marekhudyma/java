package mh.java8.dateTime;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class DateTimeTest {

    @Test
    public void testLocalDate() throws Exception {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        boolean leap = date.isLeapYear();
        LocalDate now = LocalDate.now();
    }

    @Test
    public void testLocalTime() throws Exception {
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
    }

    @Test
    public void testLocalDateTime() throws Exception {
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 14, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dt3 = LocalDate.now().atTime(13, 45, 20);
        LocalDateTime dt4 = LocalTime.now().atDate(LocalDate.of(2014, 3, 18));
    }

    @Test
    public void testInstant() throws Exception {
        Instant instant1 = Instant.ofEpochSecond(3); // seconds from the epoch of 1970-01-01T00:00:00Z.
        Instant instant2 = Instant.ofEpochSecond(3, 0);
        Instant instant3 = Instant.ofEpochSecond(2, 1_000_000_000);
        Instant instant4 = Instant.ofEpochSecond(4, -1_000_000_000);
    }

    @Test
    public void testDuration() throws Exception {
        Duration d1 = Duration.between(LocalTime.of(13, 45, 20), LocalTime.of(14, 00, 30));
        Duration d2 = Duration.between(LocalDateTime.of(2014, 3, 14, 13, 45, 20),
                                       LocalDateTime.of(2014, 3, 15, 16, 47, 8));
        Duration d3 = Duration.between(Instant.ofEpochSecond(3), Instant.ofEpochSecond(2));
    }

    @Test
    public void testPeriod() throws Exception {
        Period tenDays = Period.between(LocalDate.of(2013, 3, 8), LocalDate.of(2014, 3, 18));
        System.out.println(tenDays); //P1Y10D
    }

    @Test
    public void testManipulation() throws Exception {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.plusWeeks(3);
    }

    @Test
    public void testAdjusters() throws Exception {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY)); //2014-03-23
        LocalDate date3 = date2.with(lastDayOfMonth());  //2014-03-31
    }

    @Test
    public void testPrinting() throws Exception {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE); //20140318
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE); //2014-03-18
    }

    @Test
    public void testParsing() throws Exception {
        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate date3 = LocalDate.parse("18/03/2014", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Test
    public void testZonedDateTime() throws Exception {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2014, 3, 18, 0, 0, 0, 0, ZoneId.of("America/Los_Angeles"));
    }

}
