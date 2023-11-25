
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
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String Gender;

    @Column(nullable = false, length = 100)
    private String phoneNumber;

    @Column(nullable = false, length = 100)
    private String email;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] profilePhoto;


    @Column(nullable = false, length = 100)
    private String skills;

    @Column(nullable = false, length = 100)
    private String experience;

    @JsonIgnore
    @OneToOne
    @JoinColumn(unique = true, name = "profile_id", referencedColumnName = "id")
    private AppUser appUser;

    @OneToMany(mappedBy = "profile")
    private List<Applied> appliedList = new ArrayList<>();


}


