package ru.itis.darZam.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryPercentDto {

    private String name;
    private Integer percent;
    private Long id;
}
