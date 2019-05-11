package ru.itis.darZam.models;


import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Entity
@Getter
@Setter
public class Category {

    private String name;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String icon;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "category")
    private Set<Transaction> transactions;
}

