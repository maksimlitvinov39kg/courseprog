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
public class Transactions {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String tranDatetime;
    private int mccCode;
    private int tranType;
    private Long amount;
    private Long termId;

    public String toString(){
        return(customerId.toString() + ","
                + tranDatetime + ","
                + mccCode + ","
                + tranType + ","
                + amount.toString() + ','
                + termId.toString());
    }

}
