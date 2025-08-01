package testingDates;

import java.util.Scanner;

public class Date implements Cloneable {
    private String month;
    private int day;
    private int year; // a four digit number

    // Constructors
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
        day   = 1;
        this.year = year;
    }

    public Date(Date aDate) {
        if (aDate == null) {
            System.out.println("Fatal Error in Date(Date).");
            System.exit(0);
        }
        this.month = aDate.month;
        this.day   = aDate.day;
        this.year  = aDate.year;
    }

    // Clone support for snapshots in tests
    @Override
    public Date clone() {
        return new Date(this);
    }

    // --- Step 9: string‑month setDate returns this or null --- //
    public Date setDate(String monthString, int day, int year) {
        if (!dateOK(monthString, day, year)) {
            return null;               // illegal input → do nothing, return null
        }
        // legal input → apply and return this
        this.month = monthString;
        this.day   = day;
        this.year  = year;
        return this;
    }
    // -------------------------------------------------------- //

    // numeric‑month overload remains void
    public void setDate(int monthInt, int day, int year) {
        if (dateOK(monthInt, day, year)) {
            this.month = monthString(monthInt);
            this.day   = day;
            this.year  = year;
        }
        // illegal input → ignore
    }

    public void setDate(int year) {
        setDate(1, 1, year);
    }

    public void setYear(int year) {
        if (year >= 1000 && year <= 9999) {
            this.year = year;
        }
        // else ignore
    }

    public void setMonth(int monthNumber) {
        if (monthNumber >= 1 && monthNumber <= 12) {
            this.month = monthString(monthNumber);
        }
        // else ignore
    }

    public void setDay(int day) {
        if (day >= 1 && day <= 31) {
            this.day = day;
        }
        // else ignore
    }

    // Getters
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
        if (this == obj)            return true;
        if (!(obj instanceof Date)) return false;
        Date other = (Date) obj;
        return this.year == other.year
            && this.day  == other.day
            && this.month.equals(other.month);
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

    // Helpers
    private boolean dateOK(int monthInt, int dayInt, int yearInt) {
        return monthInt >= 1 && monthInt <= 12
            && dayInt   >= 1 && dayInt   <= 31
            && yearInt  >= 1000 && yearInt <= 9999;
    }

    private boolean dateOK(String m, int dayInt, int yearInt) {
        if (!monthOK(m) || yearInt < 1000 || yearInt > 9999) {
            return false;
        }
        int monthNum;
        switch (m) {
            case "January":   monthNum = 1;  break;
            case "February":  monthNum = 2;  break;
            case "March":     monthNum = 3;  break;
            case "April":     monthNum = 4;  break;
            case "May":       monthNum = 5;  break;
            case "June":      monthNum = 6;  break;
            case "July":      monthNum = 7;  break;
            case "August":    monthNum = 8;  break;
            case "September": monthNum = 9;  break;
            case "October":   monthNum = 10; break;
            case "November":  monthNum = 11; break;
            case "December":  monthNum = 12; break;
            default:          return false;
        }
        int maxDay;
        if (monthNum == 2) {
            maxDay = 28;
        } else if (monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11) {
            maxDay = 30;
        } else {
            maxDay = 31;
        }
        return dayInt >= 1 && dayInt <= maxDay;
    }

    private boolean monthOK(String m) {
        return m.equals("January")  || m.equals("February")
            || m.equals("March")    || m.equals("April")
            || m.equals("May")      || m.equals("June")
            || m.equals("July")     || m.equals("August")
            || m.equals("September")
            || m.equals("October")
            || m.equals("November")
            || m.equals("December");
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
            default: return "Error"; // unreachable
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
        this.year  = y;
        this.month = monthString(m);
        this.day   = d;
        return this;
    }

    public static void main(String[] args) {
        System.out.println("Main in Date.");
        Date tester = new Date();
        System.out.println("tester is " + tester);
    }
}
