package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.h2.command.dml.Update;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime appliedDate;


}
