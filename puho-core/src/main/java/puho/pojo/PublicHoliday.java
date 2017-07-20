package puho.pojo;

import java.time.LocalDate;

public class PublicHoliday implements Comparable<PublicHoliday> {

    private String name;

    private LocalDate date;


    public PublicHoliday(final String nameValue, final LocalDate dateValue) {
        this.name = nameValue;
        this.date = dateValue;
    }


    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
    @Override
    public int compareTo(final PublicHoliday o) {
        return this.date.compareTo(o.getDate());
    }
}
