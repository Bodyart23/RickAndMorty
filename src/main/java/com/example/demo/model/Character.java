package com.example.demo.model;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Getter
@Setter
@Table(name = "character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String status;
    @Column
    private String species;
    @Column
    private String type;
    @Column
    private String gender;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id")
    private Origin origin;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;
    @Column
    private String image;
    @Column
    private ArrayList episode;
    @Column
    private String url;
    @Column
    private String created;

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", origin=" + origin +
                ", location=" + location +
                ", image='" + image + '\'' +
                ", episode=" + episode +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
