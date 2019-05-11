package ru.itis.darZam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "persistent_logins")
public class Remember {

    @Column(nullable = false, length = 100)
    private String username;

    @Id
    @Column(nullable = false, length = 64)
    private String series;

    @Column(nullable = false, length = 64)
    private String token;

    @Column(nullable = false)
    private Timestamp last_used;
}
