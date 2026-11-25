package com.mangatracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

/*      Series Entity - maps to sql

        id                  - pk
        title               - Manga title
        author              - Author name
        status              - Reading status
        total_volumes       - Num of volumes
        cover_url           - The Url for cover image
        rating              - Manga Rating
        created_at          - Creation date

        @Entity             - marked as a JPA db entity
        @Id                 - primary key
        @GeneratedValue     - auto increment db assigned value
        @Column             - db column
        @Enumerated         - store the enums names
        @PrePersist         - for setting creation date values
 */

@Entity
@Table(name = "series")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private SeriesStatus status;

    @Column(name = "total_volumes")
    private Integer totalVolumes;

    @Column(name = "cover_url")
    private String coverUrl;

    private Short rating;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


}
