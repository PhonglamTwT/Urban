package com.example.Urban.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EventDTO {
    private int id;
    private Date day;
    private String status;
    private String workplace;
    private String worktime;
}
