package com.ebit.tree.family.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer generation;

    @Column(length = 1)
    private String gender; // 'M' or 'F'

    @Temporal(TemporalType.DATE)
    private Date birthdate;

    private boolean alive;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private byte[] image;

    // Self-referencing relationship to represent the person's mother
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_id")
    @JsonIgnore
    private Person mother;

    // Self-referencing relationship to represent the person's father
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_id")
    @JsonIgnore
    private Person father;

    // Reverse relationship to get all children of this person (mappedBy is used to avoid a join table)
    @OneToMany(mappedBy = "mother", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Person> childrenFromMother;

    @OneToMany(mappedBy = "father", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Person> childrenFromFather;

    @PrePersist
    public void onPrePersist(){
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.setAlive(true);
    }

    @PreUpdate
    public void onPreUpdate(){
        this.setUpdatedAt(LocalDateTime.now());
    }

}
