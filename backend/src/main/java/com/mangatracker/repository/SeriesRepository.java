package com.mangatracker.repository;

import com.mangatracker.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

/*
        SeriesRepository - Data Access Layer, (CRUD operations)


          - findAll()
          - findById(id)
          - save(entity)
          - delete(entity)

*/
public interface SeriesRepository extends JpaRepository<Series, Long> {

}
