package tech.abede.todoapi.todo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    @NotBlank(message = "Todo description can't be blank")
    private String description;

    public Todo convertToEntity(){
        return Todo.builder().description(description).build();
    }
}
