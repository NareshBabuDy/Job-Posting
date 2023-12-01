package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String username;

    @Column(nullable = false, length = 100)
    @Size(min = 2, message = "Password should have at least 2 characters")
    private String password;

    @Column(nullable = false, length = 100)
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "appUser")
    private Profile profile;

//    @OneToMany(mappedBy = "appUser")
//    private List<Applied> appliedList = new ArrayList<>();

    @OneToOne(mappedBy = "appUser")
    private Company company;


}
