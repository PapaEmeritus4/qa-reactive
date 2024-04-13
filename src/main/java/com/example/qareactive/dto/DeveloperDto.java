package com.example.qareactive.dto;

import com.example.qareactive.entity.DeveloperEntity;
import com.example.qareactive.entity.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeveloperDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String specialty;
    private Status status;

    public static DeveloperDto fromEntity(DeveloperEntity entity) {
        return DeveloperDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .specialty(entity.getSpecialty())
                .status(entity.getStatus())
                .build();
    }

    public DeveloperEntity toEntity() {
        return DeveloperEntity.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .specialty(specialty)
                .status(status)
                .build();
    }
}
