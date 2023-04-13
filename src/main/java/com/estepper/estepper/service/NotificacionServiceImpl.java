package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.repository.NotificacionRepository;

@Service
public class NotificacionServiceImpl implements NotificacionService{
    
    @Autowired
    private NotificacionRepository repo;

}
