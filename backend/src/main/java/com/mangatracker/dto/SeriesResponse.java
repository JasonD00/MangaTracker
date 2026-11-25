package com.mangatracker.dto;

import com.mangatracker.entity.SeriesStatus;

import java.time.LocalDateTime;


/*
        SeriesResponse - DTO returned

        Maps from Series entity



        id              - Database primary key
        title           - Manga title
        author          - Author name
        status          - Current reading status
        totalVolumes    - Number of volumes
        coverUrl        - Cover image URL
        rating          - Rating (1-10)
        createdAt       - When the series was created
*/

public record SeriesResponse (
        Long id,
        String title,
        String author,
        SeriesStatus status,
        Integer totalVolumes,
        String coverUrl,
        Integer rating,
        LocalDateTime createdAt
)
{

}
