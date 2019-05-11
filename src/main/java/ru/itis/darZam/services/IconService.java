package ru.itis.darZam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.darZam.models.Icon;
import ru.itis.darZam.repository.IconRepository;

import java.util.List;

@Service
public class IconService {

    @Autowired
    private IconRepository iconRepository;

    public List<Icon> getIcons(){
        return iconRepository.findAll();
    }
}
