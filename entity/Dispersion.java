package com.example.courseprog.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Dispersion {
    @Id
    private int mccType;

    private double dispersion;
}
