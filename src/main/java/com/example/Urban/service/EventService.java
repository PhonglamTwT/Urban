package com.example.Urban.service;

import com.example.Urban.dto.EmployeeAccountDTO;
import com.example.Urban.dto.EventDTO;
import com.example.Urban.entity.EventEntity;

import java.util.List;

public interface EventService {
    public List<EventEntity> getAllEvent();
    //public List<EventDTO> getByEmployee(int id);
    public Boolean updateEvent(EventDTO eventDTO);
    public Boolean deleteEvent(int employeeId);
    public EventEntity createEvent(EventDTO eventDTO);
}
