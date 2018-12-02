package models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String name;
    private Long id;
    private String img;
    private Color color;
    private Float percent;
}

