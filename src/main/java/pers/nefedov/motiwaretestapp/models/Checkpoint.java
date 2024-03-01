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
@Table(name="checkpoints")
public class Checkpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkpointsIdSeq")
    @SequenceGenerator(name = "checkpointsIdSeq", sequenceName = "checkpoints_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="finish_date")
    private Date finishDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
