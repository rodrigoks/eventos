package br.com.jbusiness.eventos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbusiness.eventos.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, UUID> {

}
