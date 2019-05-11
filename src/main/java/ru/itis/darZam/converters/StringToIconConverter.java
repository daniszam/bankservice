package ru.itis.darZam.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.darZam.models.Icon;
import ru.itis.darZam.repository.IconRepository;

import java.util.Optional;

@Component
public class StringToIconConverter implements Converter<String, Icon> {

    @Autowired
    private IconRepository iconRepository;

    @Override
    public Icon convert(String s) {
        try{
            Long iconId = Long.parseLong(s);
            Optional<Icon> iconOptional = iconRepository.findById(iconId);
            return iconOptional.orElseGet(() -> iconRepository.findById(0l).get());
        } catch (NumberFormatException e){
            return iconRepository.findById(0l).get();
        }
    }
}
