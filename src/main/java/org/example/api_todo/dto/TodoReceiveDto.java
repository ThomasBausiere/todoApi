package org.example.api_todo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_todo.entity.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoReceiveDto {
    private UUID id;
    private String title;
    private String description;
    private String createdDate;
    private boolean isValidate;

    public Todo dtoToEntity(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return Todo.builder()

                .title(this.getTitle())
                .description(getDescription())
                .createdDate(LocalDate.parse(getCreatedDate(),dateTimeFormatter))
                .isValidate(isValidate())
                .build();
    }

}
