package com.example.qareactive.rest;

import com.example.qareactive.dto.DeveloperDto;
import com.example.qareactive.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {

    private final DeveloperService developerService;

    @PostMapping
    public Mono<?> createDeveloper(@RequestBody DeveloperDto developerDto) {
        return developerService.createDeveloper(developerDto.toEntity())
                .flatMap(developer -> Mono.just(DeveloperDto.fromEntity(developer)));
    }

    @PutMapping
    public Mono<?> updateDeveloper(@RequestBody DeveloperDto developerDto) {
        return developerService.updateDeveloper(developerDto.toEntity())
                .flatMap(entity -> Mono.just(DeveloperDto.fromEntity(entity)));
    }

    @GetMapping
    public Flux<?> getAllDevelopers() {
        return developerService.getAll()
                .flatMap(entity -> Mono.just(DeveloperDto.fromEntity(entity)));
    }

    @GetMapping("/specialty/{specialty}")
    public Flux<?> getAllBySpecialty(@PathVariable("specialty") String specialty) {
        return developerService.findAllActiveBySpecialty(specialty)
                .flatMap(entity -> Mono.just(DeveloperDto.fromEntity(entity)));
    }

    @GetMapping("/{id}")
    public Mono<?> getAllBySpecialty(@PathVariable("id") Integer id) {
        return developerService.getById(id)
                .flatMap(entity -> Mono.just(DeveloperDto.fromEntity(entity)));
    }

    @DeleteMapping("/{id}")
    public Mono<?> deleteById(@PathVariable("id") Integer id,
                              @RequestParam(value = "isHard", defaultValue = "false") boolean isHard) {
        if (isHard) {
            return developerService.hardDeleteById(id);
        }
        return developerService.softDeleteById(id);
    }
}
