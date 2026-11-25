package com.mangatracker.service;

import com.mangatracker.dto.SeriesRequest;
import com.mangatracker.dto.SeriesResponse;
import com.mangatracker.entity.Series;
import com.mangatracker.exception.ResourceNotFoundException;
import com.mangatracker.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*

            https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html

            SeriesService

            findAll()           - returns list of all series as SeriesResponse
            findById(id)        - returns one series or throws exception
            create(req)         - creates new series from SeriesRequest
            update(id,req)      - updates existing series
            delete(id)          - deletes series by id

            Helpers:

            getOrThrow(id)
            apply
            toResponse
*/

@Service
@Transactional
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    // Find all series - is read only
    @Transactional(readOnly = true)
    public List<SeriesResponse> findAll() {
        // for each series, add to response list
        return seriesRepository.findAll().stream().map(this::toResponse).toList();
    }

    // Find a single entry by id
    @Transactional(readOnly = true)
    public SeriesResponse findById(Long id) {
        return toResponse(getOrThrow(id)); // getOrThrow - helper function if missing
    }

   // Create a new series entry
    public SeriesResponse create(SeriesRequest request) {
        Series series = new Series(); // creation of the object
        apply(series, request); // call apply
        return toResponse(seriesRepository.save(series)); // return the response
    }

    // Update an existing series record
    public SeriesResponse update(Long id, SeriesRequest request) {
        Series series = getOrThrow(id);
        apply(series, request);
        return toResponse(seriesRepository.save(series));
    }

    // Delete a series record by ID
    public void delete(Long id) {
        seriesRepository.delete(getOrThrow(id));
    }

    // find the series or throw ResourceNotFoundException
    private Series getOrThrow(Long id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Series Not Found: " + id));
    }

    // Copy the data from Request - Entity. DTO to Entity
    private void apply(Series series, SeriesRequest request) {
        series.setTitle(request.title());
        series.setAuthor(request.author());
        series.setStatus(request.status());
        series.setTotalVolumes(request.totalVolumes());
        series.setCoverUrl(request.coverUrl());
        series.setRating(request.rating() == null ? null : request.rating().shortValue());
    }

    // converts Entities to SeriesResponse
    private SeriesResponse toResponse(Series s) {
        return new SeriesResponse(
                s.getId(),
                s.getTitle(),
                s.getAuthor(),
                s.getStatus(),
                s.getTotalVolumes(),
                s.getCoverUrl(),
                s.getRating() == null ? null : s.getRating().intValue(),
                s.getCreatedAt()
        );
    }

}
