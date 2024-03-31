package com.kpoy.housecams.repositories;

import com.kpoy.housecams.domain.MediaFile;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MediaFileRepository extends
        ReactiveSortingRepository<MediaFile, Integer>,
        ReactiveCrudRepository<MediaFile, Integer> {
    Flux<MediaFile> findByMediaDirectoryId(Integer id, Sort sort);

}
