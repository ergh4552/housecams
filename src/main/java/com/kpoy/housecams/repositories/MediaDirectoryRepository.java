package com.kpoy.housecams.repositories;

import com.kpoy.housecams.domain.MediaDirectory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MediaDirectoryRepository extends
        ReactiveSortingRepository<MediaDirectory, Integer>,
        ReactiveCrudRepository<MediaDirectory, Integer> {

    // Custom method to find all items sorted
    Flux<MediaDirectory> findAll(Sort sort);

}
