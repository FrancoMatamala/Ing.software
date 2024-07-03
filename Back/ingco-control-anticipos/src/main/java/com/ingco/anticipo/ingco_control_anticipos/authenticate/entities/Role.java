package com.ingco.anticipo.ingco_control_anticipos.authenticate.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
