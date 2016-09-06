package puho.pojo;

import java.time.LocalDate;

public class PublicHoliday implements Comparable<PublicHoliday> {

    private LocalDate date;

    private String name;

    public PublicHoliday(String name, LocalDate datePublicHoliday) {
        this.name = name;
        this.date = datePublicHoliday;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
    @Override
    public int compareTo(PublicHoliday o) {
        return this.date.compareTo(o.getDate());
    }
}
