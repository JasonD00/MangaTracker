package com.mangatracker.controller;

import com.mangatracker.dto.SeriesRequest;
import com.mangatracker.dto.SeriesResponse;
import com.mangatracker.service.SeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
        Controller Layer Mappings adapted and implemented into the project
        https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-requestmapping.html

        RestController Class

        getAll()    - Get Request
        findById    - Get Request
        create()    - POST
        update()    - PUT
        delete()    - DELETE
 */

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;

    // Get all series
    @GetMapping
    public List<SeriesResponse> getAll(){
        return seriesService.findAll();

    }

    // Get a series by id
    @GetMapping("/{id}")
    public SeriesResponse findById(Long id){

        return seriesService.findById(id);
    }

    // Create a new series
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 on Created
    public SeriesResponse create(@Valid @RequestBody SeriesRequest seriesRequest) {
        return seriesService.create(seriesRequest);
    }

    // Update an existing series
    @PutMapping("/{id}")
    public SeriesResponse update(@PathVariable Long id, @Valid @RequestBody SeriesRequest seriesRequest) {
        return seriesService.update(id, seriesRequest);
    }

    // Delete a series
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 no Content
    public void delete(@PathVariable Long id){

        seriesService.delete(id);
    }
}
