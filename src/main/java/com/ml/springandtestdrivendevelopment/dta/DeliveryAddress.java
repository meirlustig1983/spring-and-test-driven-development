package com.ml.springandtestdrivendevelopment.dta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Entity
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NonNull
    private String addressLine1;

    private String addressLine2;

    @NonNull
    private String city;

    @NonNull
    private String state;

    @NonNull
    private String zipCode;

    @NonNull
    @Builder.Default
    private Date createdDate = new Date();
}
