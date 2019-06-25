package com.example.demo.model;
import lombok.*;

import javax.persistence.*;
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "origin")
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String url;


    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
