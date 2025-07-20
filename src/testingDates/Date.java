package testingDates;

import java.util.Scanner;

public class Date {
    private String month;
    private int day;
    private int year; // a four digit number

    public Date() {
        this("January", 1, 1000);
    }

    public Date(int monthInt, int day, int year) {
        setDate(monthInt, day, year);
    }

    public Date(String monthString, int day, int year) {
        setDate(monthString, day, year);
    }

    public Date(int year) {
        month = "January";
        day = 1;
        this.year = year;
    }

    public Date(Date aDate) {
        if (aDate == null) {
            System.out.println("Fatal Error in Date(Date).");
            System.exit(0);
        }
        month = aDate.month;
        day   = aDate.day;
        year  = aDate.year;
    }

    public void setDate(int monthInt, int day, int year) {
        if (dateOK(monthInt, day, year)) {
            this.month = monthString(monthInt);
            this.day   = day;
            this.year  = year;
        } else {
            System.out.println("Fatal Error in setDate(int, int, int)");
            System.exit(0);
        }
    }

    public void setDate(String monthString, int day, int year) {
        if (dateOK(monthString, day, year)) {
            this.month = monthString;
            this.day   = day;
            this.year  = year;
        } else {
            System.out.println("Fatal Error in setDate(String,int, int)");
            System.exit(0);
        }
    }

    public void setDate(int year) {
        setDate(1, 1, year);
    }

    public void setYear(int year) {
        if (year < 1000 || year > 9999) {
            System.out.println("Fatal Error in setYear(int)");
            System.exit(0);
        } else {
            this.year = year;
        }
    }

    public void setMonth(int monthNumber) {
        if (monthNumber <= 0 || monthNumber > 12) {
            System.out.println("Fatal Error in setMonth(int)");
            System.exit(0);
        } else {
            month = monthString(monthNumber);
        }
    }

    public void setDay(int day) {
        if (day <= 0 || day > 31) {
            System.out.println("Fatal Error in setDay(int)");
            System.exit(0);
        } else {
            this.day = day;
        }
    }

    public int getMonth() {
        if (month.equals("January"))   return 1;
        if (month.equals("February"))  return 2;
        if (month.equalsIgnoreCase("March"))     return 3;
        if (month.equalsIgnoreCase("April"))     return 4;
        if (month.equalsIgnoreCase("May"))       return 5;
        if (month.equals("June"))       return 6;
        if (month.equalsIgnoreCase("July"))      return 7;
        if (month.equalsIgnoreCase("August"))    return 8;
        if (month.equalsIgnoreCase("September")) return 9;
        if (month.equalsIgnoreCase("October"))   return 10;
        if (month.equals("November"))  return 11;
        if (month.equals("December"))  return 12;

        System.out.println("Fatal Error in getMonth");
        System.exit(0);
        return 0; // unreachable
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return month + " " + day + ", " + year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)                   return true;
        if (!(obj instanceof Date))        return false;
        Date other = (Date) obj;
        return year == other.year
            && day  == other.day
            && month.equals(other.month);
    }

    public boolean precedes(Date otherDate) {
        if (year < otherDate.year) return true;
        if (year == otherDate.year && getMonth() < otherDate.getMonth()) return true;
        return year == otherDate.year
            && month.equals(otherDate.month)
            && day < otherDate.day;
    }

    public void readInput() {
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.println("Enter month, day, and year.");
            System.out.println("Do not use a comma.");
            String m = keyboard.next();
            int d = keyboard.nextInt();
            int y = keyboard.nextInt();
            if (dateOK(m, d, y)) {
                setDate(m, d, y);
                break;
            }
            System.out.println("Illegal date. Reenter input.");
        }
    }

    private boolean dateOK(int monthInt, int dayInt, int yearInt) {
        return monthInt >= 1 && monthInt <= 12
            && dayInt   >= 1 && dayInt   <= 31
            && yearInt  >= 1000 && yearInt <= 9999;
    }

    private boolean dateOK(String m, int dayInt, int yearInt) {
        return monthOK(m)
            && dayInt  >= 1 && dayInt  <= 31
            && yearInt >= 1000 && yearInt <= 9999;
    }

    private boolean monthOK(String m) {
        return m.equals("January")  || m.equals("February")
            || m.equals("March")    || m.equals("April")
            || m.equals("May")      || m.equals("June")
            || m.equals("July")     || m.equals("August")
            || m.equals("September")|| m.equals("October")
            || m.equals("November") || m.equals("December");
    }

    private String monthString(int monthNumber) {
        switch (monthNumber) {
            case 1:  return "January";
            case 2:  return "February";
            case 3:  return "March";
            case 4:  return "April";
            case 5:  return "May";
            case 6:  return "June";
            case 7:  return "July";
            case 8:  return "August";
            case 9:  return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default:
                System.out.println("Fatal Error in monthString");
                System.exit(0);
                return "Error"; // unreachable
        }
    }

    /** Stub for lab step 5 */
    public Date addOneDay() {
        int m = getMonth();        // numeric month 1–12
        int d = day + 1;           // next day
        int y = year;

        int maxDay;
        if (m == 2) {
            maxDay = 28;          // February fixed to 28
        } else if (m == 4 || m == 6 || m == 9 || m == 11) {
            maxDay = 30;          // Apr, Jun, Sep, Nov
        } else {
            maxDay = 31;          // the rest
        }

        if (d > maxDay) {
            d = 1;
            m++;
            if (m > 12) {
                m = 1;
                y++;
            }
        }

        year  = y;
        month = monthString(m);
        day   = d;
        return this;
    }

    public static void main(String[] args) {
        System.out.println("Main in Date.");
        Date tester = new Date();
        System.out.println("tester is " + tester);
    }
}
