package com.example.Urban.service.imp;

import com.example.Urban.dto.EventDTO;
import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.entity.EventEntity;
import com.example.Urban.repository.EventRepository;
import com.example.Urban.service.EventService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class EventServiceImp implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventEntity> getAllEvent() { return eventRepository.findAll(); }
    @Override
    public Boolean updateEvent(EventDTO eventDTO) {
        EventEntity event = eventRepository.findById(eventDTO.getId()).orElseThrow(() -> new RuntimeException("Event not found"));
        event.setName(eventDTO.getName());
        event.setDay(eventDTO.getDay());
        event.setWorktime(eventDTO.getWorktime());
        event.setWorkplace(eventDTO.getWorkplace());
        event.setStatus(eventDTO.getStatus());
        try{
            eventRepository.save(event);
            return true;
        }
        catch (Exception e){
            System.out.println("ManagerServiceImp updateEvent Error: " + e.getLocalizedMessage());
            return false;
        }
    }
    @Override
    public Boolean deleteEvent(int eventId){
        try{
            EventEntity event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
            eventRepository.delete(event);
            return true;
        }catch (Exception e){
            System.out.println("EventService deleteEvent Error: "+e.getLocalizedMessage());
            return false;
        }
    }
    @Transactional
    public EventEntity createEvent(EventDTO eventDTO){
        EventEntity event = new EventEntity();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setWorktime(eventDTO.getWorktime());
        event.setWorkplace(eventDTO.getWorkplace());
        event.setDay(eventDTO.getDay());
        event.setStatus(eventDTO.getStatus());
        eventRepository.save(event);
        return event;
    }
}
