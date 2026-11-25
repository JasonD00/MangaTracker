package com.mangatracker.dto;

import com.mangatracker.entity.SeriesStatus;
import jakarta.validation.constraints.*;

/*
        SeriesRequest - DTO for creating/updating a Series

        title           - must be filled, max 255 characters
        author          - optional input, max 255 characters
        status          - must be filled in, (SeriesStatus)
        totalVolumes    - optional input, minimum 1
        coverUrl        - cover image
        rating          - rating between 1 and 10
*/

public record SeriesRequest (

    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "TItle must be 255 charcters or fewer")
    String title,

    @Size(max = 255, message = "Author must be 255 characters or fewer")
    String author,

    @NotNull(message = "Status is required")
    SeriesStatus status,

    @Min(value = 1, message = "Total volumes mst be atleast 1")
    Integer totalVolumes,

    String coverUrl,

    @Min(value = 1, message = "Rating must be between 1 and 10")
    @Max(value = 10, message = "Rating must be between 1 and 10")
    Integer rating

    )

{

}
