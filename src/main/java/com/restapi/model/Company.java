package com.restapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String companyName;

    @Column(nullable = false, length =  100)
    private String companyUrl;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] companyPhoto;

    @JsonIgnore
    @OneToOne
    @JoinColumn(unique = true, name = "user_id", referencedColumnName = "id")
    private AppUser appUser;

    @OneToMany(mappedBy = "company")
    private List<Jobs> jobsList = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<AppliedJob> appliedJobs = new ArrayList<>();

}
