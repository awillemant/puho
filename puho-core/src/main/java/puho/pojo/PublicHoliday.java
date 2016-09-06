package puho.pojo;

import java.time.LocalDate;

public class PublicHoliday implements Comparable<PublicHoliday> {

    private LocalDate date;

    private String name;

    public PublicHoliday(LocalDate datePublicHoliday) {
        this.date = datePublicHoliday;
    }

    public PublicHoliday(String name, LocalDate datePublicHoliday) {
        this.name = name;
        this.date = datePublicHoliday;
    }

    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(PublicHoliday o) {
        return this.date.compareTo(o.getDate());
    }
}
