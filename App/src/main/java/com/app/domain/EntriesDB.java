package com.app.domain;

import com.app.dto.Status;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"entry", "status"})

@Table(name = "entries")
public class EntriesDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "entry", nullable = false)
    private String entry;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;


}