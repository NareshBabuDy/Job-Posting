package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "jobs_id"})})
public class Applied {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @ManyToOne
    @JoinColumn( referencedColumnName = "id")
    private Jobs mainJobid;

    @ManyToOne
    @JoinColumn(name = "jobs_id", referencedColumnName = "id")
    private AppliedJob jobs;

    @Column(nullable = false, length = 100)
    private String status;


}
