package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);

        String[] dateTime = at.split(" ");

        String rawDate = dateTime[0];
        String[] dayMonthYear = rawDate.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dayMonthYear[2]), Integer.parseInt(dayMonthYear[1]), Integer.parseInt(dayMonthYear[0]));
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        if (dateTime.length > 1) {
            // store date and time
            String rawTime = dateTime[1];
            String processedRawTime = rawTime.substring(0, 2) + ":" + rawTime.substring(2);
            LocalTime time = LocalTime.parse(processedRawTime);
            String formattedTime = time.format(DateTimeFormatter.ofPattern("h a"));

            this.at = formattedDate + ", " + formattedTime;
        }
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toData() {
        return "E" + super.toData() + " | " + this.at;
    }
}