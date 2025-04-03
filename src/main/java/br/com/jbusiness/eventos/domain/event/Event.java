package br.com.jbusiness.eventos.domain.event;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    private String eventUrl;
    private String imageUrl;
    private Boolean remote;
    private Date date;

    public Event(EventRequestDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.eventUrl = data.eventUrl();
        this.date = new Date(data.date());
    }

}
