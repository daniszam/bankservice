package ru.itis.darZam.models.dto;

import lombok.*;
import ru.itis.darZam.models.Category;
import ru.itis.darZam.models.Color;

//@Data
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "color")
public class CategoryDto {

    public CategoryDto(Category category, double sumOfCosts){
        this.name = category.getName();
        this.sumOfCosts = (int)sumOfCosts;
        this.id = category.getId();
        this.color = category.getColor();
    }

    private Color color;
    private String name;
    private Integer sumOfCosts;
    private Long id;
}
