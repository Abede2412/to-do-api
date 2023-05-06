package tech.abede.todoapi.todo;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TodoNotFoundException extends RuntimeException{

    public TodoNotFoundException(String message){
        super(message);
    }
}
