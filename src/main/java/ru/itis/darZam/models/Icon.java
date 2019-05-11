package ru.itis.darZam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "icon")
public class Icon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String name;

    @OneToMany(mappedBy = "icon")
    @JsonIgnore
    private Set<Balance> balance;
}
