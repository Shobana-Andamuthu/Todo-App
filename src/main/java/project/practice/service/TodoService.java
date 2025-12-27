package project.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.practice.models.Todo;
import project.practice.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo)
    {
        return todoRepository.save(todo);
    }

    public Todo GetTodo(Long id)
    {
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("Todo not Found"));
    }

    public List<Todo> getTodo()
    {
        return todoRepository.findAll();
    }

    public Page<Todo> getPage(int page , int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable);
    }

    public Todo UpdateTodo(Todo todo)
    {
        return todoRepository.save(todo);
    }

    public  void deleteTodoById(long id)
    {
        todoRepository.delete(GetTodo(id));
    }

    public  void deleteTodo(Todo todo)
    {
        todoRepository.delete(todo);
    }

}
