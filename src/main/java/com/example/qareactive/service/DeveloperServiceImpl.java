package com.example.qareactive.service;

import com.example.qareactive.entity.DeveloperEntity;
import com.example.qareactive.entity.Status;
import com.example.qareactive.exception.DeveloperNotFoundException;
import com.example.qareactive.exception.DeveloperWithEmailAlreadyExistsException;
import com.example.qareactive.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    private Mono<Void> checkIfExistByEmail(String email) {
        return developerRepository.findByEmail(email)
                .flatMap(developerEntity -> {
                    if (Objects.nonNull(developerEntity)) {
                        return Mono.error(new DeveloperWithEmailAlreadyExistsException("Developer with defined email already exists", "DEVELOPER_DUPLICATE_EMAIL"));
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<DeveloperEntity> createDeveloper(DeveloperEntity developer) {
        return checkIfExistByEmail(developer.getEmail())
                .then(Mono.defer(() -> {
                    developer.setStatus(Status.ACTIVE);
                    return developerRepository.save(developer);
                }));
    }

    @Override
    public Mono<DeveloperEntity> updateDeveloper(DeveloperEntity developer) {
        return developerRepository.findById(developer.getId())
                .switchIfEmpty(Mono.error(new DeveloperNotFoundException("Developer not found", "DEVELOPER_NOT_FOUND")))
                .flatMap(d -> developerRepository.save(developer));
    }

    @Override
    public Flux<DeveloperEntity> getAll() {
        return developerRepository.findAll();
    }

    @Override
    public Flux<DeveloperEntity> findAllActiveBySpecialty(String specialty) {
        return developerRepository.findAllActiveBySpecialty(specialty);
    }

    @Override
    public Mono<DeveloperEntity> getById(Integer id) {
        return developerRepository.findById(id);
    }

    @Override
    public Mono<Void> softDeleteById(Integer id) {
        return developerRepository.findById(id)
                .switchIfEmpty(Mono.error(new DeveloperNotFoundException("Developer not found", "DEVELOPER_NOT_FOUND")))
                .flatMap(developer -> {
                    developer.setStatus(Status.DELETED);
                    return developerRepository.save(developer).then();
                });
    }

    @Override
    public Mono<Void> hardDeleteById(Integer id) {
        return developerRepository.findById(id)
                .switchIfEmpty(Mono.error(new DeveloperNotFoundException("Developer not found", "DEVELOPER_NOT_FOUND")))
                .flatMap(developer -> developerRepository.deleteById(id).then());
    }
}
