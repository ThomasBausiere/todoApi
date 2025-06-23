package org.example.api_todo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_todo.dto.TodoResponseDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;
    private LocalDate createdDate;
    private boolean isValidate;

    public TodoResponseDto entityToDto(){

        return TodoResponseDto.builder()
                .id(getId())
                .title(getTitle())
                .description(getDescription())
                .createdDate(getCreatedDate())
                .isValidate(isValidate())
                .build();
    }

}
