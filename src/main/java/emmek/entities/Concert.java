package emmek.entities;


import emmek.enumType.EventType;
import emmek.enumType.Genre;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "concerts")
public class Concert extends Event {

    private Genre genre;
    private boolean isStreaming;

    public Concert() {
    }

    public Concert(String title, LocalDate eventDate, String description, EventType eventType, int maxParticipants, Location location, Genre genre, boolean isStreaming) {
        super(title, eventDate, description, eventType, maxParticipants, location);
        this.genre = genre;
        this.isStreaming = isStreaming;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isStreaming() {
        return isStreaming;
    }

    public void setStreaming(boolean streaming) {
        isStreaming = streaming;
    }


}
