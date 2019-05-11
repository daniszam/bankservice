package ru.itis.darZam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "color")
@Entity
public class Color {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Short red;

    private Short blue;

    private Short green;

    @OneToMany(mappedBy = "color")
    @JsonIgnore
    private Set<Category> categorySet;
}
