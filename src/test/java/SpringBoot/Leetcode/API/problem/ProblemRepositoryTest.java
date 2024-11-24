package SpringBoot.Leetcode.API.problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static SpringBoot.Leetcode.API.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(ProblemRepository.class)
@AutoConfigureTestDatabase
class ProblemRepositoryTest {

    @Autowired
    ProblemRepository repository;

    @BeforeEach
    void setUp(){
        repository.create(PROBLEM1);
        repository.create(PROBLEM2);
    }

    @Test
    void shouldFindAllProblems() {
        List<Problem> problems = repository.findAll();
        assertEquals(2,problems.size());
    }

    @Test
    void shouldFindProblemWithValidId(){
        Optional<Problem> problem = repository.findById(1);
        assertEquals("Remove Duplicates from Sorted Array II", problem.get().name());
        assertEquals(ProblemType.ARRAY, problem.get().type());
    }

    @Test
    void shouldNotFindProblemWithInvalidId(){
        Optional<Problem> problem = repository.findById(3);
        assertTrue(problem.isEmpty());
    }

    @Test
    void shouldFindProblemByType(){
        List<Problem> problems = repository.findByType(String.valueOf(ProblemType.ARRAY));
        assertEquals("Remove Duplicates from Sorted Array II", problems.get(0).name());
    }

    @Test
    void shouldCreateProblem(){
        repository.create(PROBLEM3);
        assertEquals(3,repository.count());
    }

    @Test
    void shouldUpdateProblem(){
        repository.update(UPDATED_PROBLEM1,1);
        assertEquals("Note 5",repository.findById(1).get().notes());
    }

    @Test
    void shouldDeleteProblem(){
        repository.delete(1);
        assertEquals(1,repository.count());
    }
}