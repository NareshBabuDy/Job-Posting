package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppliedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = true , nullable = false, length = 100)
    private Long Jobid;

    @Column(nullable = false, length = 100)
//    @Size(min = 20, message = "Title should have at least 20 characters")
    private String title;

    @Column(nullable = false, length = 100)
//    @Size(min = 20, message = "Description should have at least 20 characters")
    private String description;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false, length = 100)
    private String lastdate;

    @Column(nullable = false, updatable = false, length = 100)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Long category;

    @Column(nullable = false)
    private String categoryName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "jobs")
    private List<Applied> appliedList = new ArrayList<>();

}
