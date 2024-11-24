package SpringBoot.Leetcode.API.problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static SpringBoot.Leetcode.API.constants.Constants.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ProblemController.class)
class ProblemControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    ProblemRepository repository;

    List<Problem> problems = new ArrayList<>();
    @BeforeEach
    void setUp(){
        problems.add(PROBLEM1);
        problems.add(PROBLEM2);
    }

    @Test
    void shouldFindAllProblems() throws Exception {
        when(repository.findAll()).thenReturn(problems);
        mvc.perform(MockMvcRequestBuilders.get(BASE_ROUTE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(problems.size())));
    }

    @Test
    void shouldFindProblemWithValidId() throws Exception {
        Problem problem = problems.get(0);
        when(repository.findById(1)).thenReturn(Optional.ofNullable(problem));
        mvc.perform(MockMvcRequestBuilders.get(BASE_ROUTE + "/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(problem.id())))
                .andExpect(jsonPath("$.name", is(problem.name())));
    }

    @Test
    void shouldNotFindProblemWithInvalidId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(BASE_ROUTE + "/id/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFindByType() throws Exception {
        List<Problem> arrayProblems = List.of(PROBLEM1);
        when(repository.findByType(String.valueOf(ProblemType.ARRAY))).thenReturn(arrayProblems);
        mvc.perform(MockMvcRequestBuilders.get(BASE_ROUTE + "/type/ARRAY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].type", is(ProblemType.ARRAY.toString())));
    }

    @Test
    void shouldCreateProblem() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(BASE_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PROBLEM3))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateProblem() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put(BASE_ROUTE + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(UPDATED_PROBLEM1))
            )
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteProblem() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(BASE_ROUTE + "/1"))
                .andExpect(status().isNoContent());
    }
}