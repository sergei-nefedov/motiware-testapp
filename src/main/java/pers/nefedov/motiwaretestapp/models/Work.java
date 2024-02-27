package pers.nefedov.motiwaretestapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="works")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="finish_date")
    private Date finishDate;

    @Column(name="average_completion_percentage")
    private double averageCompletionPercentage;

    @Column(name="implementer")
    private String implementer;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "checkpoint_id")
    private Checkpoint checkpoint;
}
