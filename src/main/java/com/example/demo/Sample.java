package com.example.demo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "sample")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sample {

    @Id
    @Column(name = "id" , unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]{3,12}$" ,message = "Name Incoreect")
    private String name;

    @Column(name = "dob")
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "test_id" ,referencedColumnName = "id")
    private Test test_id;
}
