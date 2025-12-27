package project.practice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.practice.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
