package com.example.qareactive.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("developers")
public class DeveloperEntity implements Persistable<Integer> {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String specialty;

    private Status status;

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }
}
