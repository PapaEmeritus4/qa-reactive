package com.example.qareactive.repository;

import com.example.qareactive.entity.DeveloperEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeveloperRepository extends R2dbcRepository<DeveloperEntity, Integer> {

    Mono<DeveloperEntity> findByEmail(String email);

    @Query("select d from developers d where d.status = 'ACTIVE' and d.specialty = ?1")
    Flux<DeveloperEntity> findAllActiveBySpecialty(String specialty);
}
