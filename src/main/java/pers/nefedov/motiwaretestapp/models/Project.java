package pers.nefedov.motiwaretestapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="number")
    private String number;

    @Column(name="condition")
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Column(name="average_completion_percentage")
    private double averageCompletionPercentage;

    @Column(name="director")
    private String director;


//    private List<Work> works;
//    private List<Checkpoint> checkpoints;
}
