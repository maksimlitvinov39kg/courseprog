package com.example.courseprog.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private int gender;

    public String toString(){
        return (customerId.toString() + "," + gender);
    }
}
