package com.ebit.tree.family.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {
    private Long id;

    private String name;

    private Integer generation;

    private String gender;

    private Date birthdate;
}
