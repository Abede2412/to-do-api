package tech.abede.todoapi.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createOne(Todo newTodo){
        return todoRepository.save(newTodo);
    }

    public List<Todo> getAll(Optional<Boolean> optionalBoolean) {
        if (optionalBoolean.isPresent()){
            return todoRepository.findAll(optionalBoolean.get());
        }
        return todoRepository.findAll();
    }

    public Todo updateOne(Integer id) {
        Todo existingTodo = findOneById(id);
        existingTodo.setFinish(true);
    
        return todoRepository.save(existingTodo);
    }

    private Todo findOneById(Integer id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()){
            return optionalTodo.get();
        }
        throw new TodoNotFoundException("Todo Not Found");
    }
    
}
