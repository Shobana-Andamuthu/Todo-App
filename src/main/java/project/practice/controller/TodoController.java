package project.practice.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.practice.service.TodoService;
import project.practice.models.Todo;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
   private TodoService todoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Todo Retrieved"),
            @ApiResponse(responseCode = "404",description="Todo not found")
    })

    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable Long id)
    {
        try {
            //Todo createdTodo=todoService.GetTodo(id);
            return new ResponseEntity<>(todoService.GetTodo(id), HttpStatus.OK);
        }catch( RuntimeException exception )
        {
            log.info("Error");
            log.warn("Warning");
            log.error("Error",exception);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    ResponseEntity<List<Todo>> GetTodos()
    {
        return  new ResponseEntity<List<Todo>>(todoService.getTodo(), HttpStatus.OK);
    }

    @GetMapping("/Page")
    ResponseEntity<Page<Todo>> pagingTodos(@RequestParam int page,@RequestParam int size)
    {
        return new ResponseEntity<>(todoService.getPage(page,size), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Todo> TodoUser( @RequestBody Todo todo){
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @PutMapping
     ResponseEntity<Todo> updateTodoById( @RequestBody Todo todo)
    {
        return new ResponseEntity<>(todoService.UpdateTodo(todo), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable int id)
    {
         todoService.deleteTodoById(id);
    }


}
