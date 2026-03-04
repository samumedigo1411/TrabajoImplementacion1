package com.name.trabajo1implementacion.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "victims")
public class Victim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String linage;
    private String crimeSceneDetails;
}
