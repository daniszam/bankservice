package services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.Icon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.IconRepository;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class IconService {


    @Autowired
    private IconRepository iconRepository;

    public List<Icon> getIcons(){
        return iconRepository.findAll();
    }
}
