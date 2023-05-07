package tech.abede.todoapi.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins= "http://localhost:3000",allowedHeaders = "*")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    @Operation(summary = "create todo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",description = "Todo is created", 
            content= @Content(mediaType = "application/json",
                schema = @Schema(implementation = Todo.class),
                examples = @ExampleObject())),
        @ApiResponse(responseCode = "400", description = "invalid request")
    })
    public ResponseEntity<Todo> createOne(@Valid @RequestBody TodoRequest todoRequest){
        Todo newTodo = todoRequest.convertToEntity();
        newTodo = todoService.createOne(newTodo);
        return ResponseEntity.status(201).body(newTodo);
    }
    @GetMapping("/todos")
    @Operation(summary = "Get list todo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "list todo are found", 
            content= @Content(mediaType = "application/json",schema = @Schema(implementation = Todo.class))),
        @ApiResponse(responseCode = "404", description = "list todo not found")
    })
    public ResponseEntity<List<Todo>> getAll(@RequestParam("finish") Optional<Boolean> optionalBoolean){
        List<Todo> todos = todoService.getAll(optionalBoolean);
        if (todos.size() == 0){
            throw new TodoNotFoundException("Todo not found");
        }
        return ResponseEntity.ok().body(todos);  
    }

    @PutMapping("/todos/{id}")
    @Operation(summary = "update todo by it's id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "todo is updated", 
            content= @Content(mediaType = "application/json",schema = @Schema(implementation = Todo.class))),
        @ApiResponse(responseCode = "404", description = "todo is not found"),
        @ApiResponse(responseCode = "400", description = "invalid todo id")
    })
    public ResponseEntity<Todo> updateOne(@PathVariable("id") Integer id){
        Todo updateTodo = todoService.updateOne(id);
        return ResponseEntity.ok().body(updateTodo);
    }
}
