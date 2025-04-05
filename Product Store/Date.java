package Liadsalhi;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(Date other) { ////copy constructor
        year = other.year;
        month = other.month;
        day = other.day;
    }

    public int getMonth() {
        return month;
    }

    public boolean setMonth(int month) {
        this.month = month;
        return true;
    }

    public int getYear() {
        return year;
    }

    public boolean setYear(int year) {
        this.year = year;
        return true;
    }

    public int getDay() {
        return day;
    }

    public boolean setDay(int day) {
        this.day = day;
        return true;
    }

    @Override
    public String toString() {
        return day + "." + month + "." + year;
    }
}
