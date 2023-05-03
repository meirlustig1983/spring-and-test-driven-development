package com.ml.springandtestdrivendevelopment.dta;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.Date;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer_contact", indexes = {
        @Index(name = "idx_customer_contact_id_email", columnList = "id, email")
})
@BatchSize(size = 10)
public class CustomerContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

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
