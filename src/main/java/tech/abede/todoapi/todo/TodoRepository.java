package tech.abede.todoapi.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo, Integer>{

    @Query(value = "select * from todo where is_finish = ?1", nativeQuery = true)
    public List<Todo> findAll(Boolean boolean1);

    @Override
    @Query(value = "select * from todo order by (case when is_finish is true then 1 else 0 end)", nativeQuery =  true)
    public List<Todo> findAll();

    


 
    
}