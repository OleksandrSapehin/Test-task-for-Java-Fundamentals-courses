package com.example.testtask.rep;

import com.example.testtask.models.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleRepository extends JpaRepository<Example,Integer> {
     List<Example> findAllByRoots(Double roots);

     @Query("SELECT e FROM Example e WHERE e.roots IN (SELECT e.roots FROM Example e GROUP BY e.roots HAVING COUNT(e) = 1)")
     List<Example> findUniqueRootsAndExamples();

}
