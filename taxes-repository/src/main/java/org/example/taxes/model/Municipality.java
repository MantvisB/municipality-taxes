package org.example.taxes.model;

import javax.persistence.*;

@Entity
@Table(name = "municipality")
public class Municipality {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public Municipality setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Municipality setName(String name) {
        this.name = name;
        return this;
    }
}


