package testingDates;

import org.junit.Test;
import static org.junit.Assert.*;

public class DateAddDaysTest {

    @Test public void stayInSameMonth() {
        Date d = new Date("June", 10, 2019);
        d.addOneDay();
        assertEquals(new Date("June", 11, 2019), d);
    }

    @Test public void stayInSameMonth_returnValue() {
        Date d = new Date("June", 10, 2019);
        assertEquals(new Date("June", 11, 2019), d.addOneDay());
    }

    @Test public void crossMonthBoundary_AprToMay() {
        Date d = new Date("April", 30, 2020);
        d.addOneDay();
        assertEquals(new Date("May", 1, 2020), d);
    }

    @Test public void crossMonthBoundary_FebToMar() {
        Date d = new Date("February", 28, 2021);
        d.addOneDay();
        assertEquals(new Date("March", 1, 2021), d);
    }

    @Test public void crossYearBoundary() {
        Date d = new Date("December", 31, 2022);
        d.addOneDay();
        assertEquals(new Date("January", 1, 2023), d);
    }
}
