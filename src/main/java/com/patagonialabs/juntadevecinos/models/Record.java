package com.patagonialabs.juntadevecinos.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", length = 50, nullable = false)
    private String author;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "creation_date", nullable = false)
    private Date creation_date;

    @Column(name = "update_date", nullable = false)
    private Date update_date;
}
