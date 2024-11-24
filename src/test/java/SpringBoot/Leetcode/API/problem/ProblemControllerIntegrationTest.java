package SpringBoot.Leetcode.API.problem;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

import static SpringBoot.Leetcode.API.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProblemControllerIntegrationTest {
    @LocalServerPort
    int randomPort;

    RestClient restClient;

    @BeforeEach
    void setUp(){
        this.restClient = RestClient.create("http://localhost:" + randomPort);
    }

    @Test
    @Order(1)
    void shouldFindAllProblems(){
        List<Problem> problems = this.restClient.get()
                .uri(BASE_ROUTE)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        assertEquals(2,problems.size());
    }

    @Test
    @Order(2)
    void shouldFindProblemWithValidId(){
        Problem problem = this.restClient.get()
                .uri(BASE_ROUTE+"/id/1")
                .retrieve()
                .body(Problem.class);
        assertAll(
                ()->assertEquals(1, problem.id()),
                ()->assertEquals("Remove Duplicates from Sorted Array II", problem.name()),
                ()->assertEquals(ProblemType.ARRAY, problem.type())
        );
    }

    @Test
    @Order(3)
    void shouldFindProblemsByType(){
        List<Problem> problems = this.restClient.get()
                .uri(BASE_ROUTE + "/type/ARRAY")
                .retrieve()
                .body(new ParameterizedTypeReference <>(){});
        assertEquals(1,problems.size());
        assertEquals(ProblemType.ARRAY,problems.get(0).type());
    }

    @Test
    @Order(4)
    void shouldReturnCount(){
        int count = this.restClient.get()
                .uri(BASE_ROUTE + "/count")
                .retrieve()
                .body(new ParameterizedTypeReference <>(){});
        assertEquals(2,count);
    }

    @Test
    @Order(5)
    void shouldCreateProblem(){
        ResponseEntity<Void> response = this.restClient.post()
                .uri(BASE_ROUTE)
                .body(PROBLEM3)
                .retrieve()
                .toBodilessEntity();
        assertEquals(201,response.getStatusCode().value());
    }

    @Test
    @Order(6)
    void shouldUpdateProblem(){
        ResponseEntity<Void> response = this.restClient.put()
                .uri(BASE_ROUTE + "/1")
                .body(UPDATED_PROBLEM1)
                .retrieve()
                .toBodilessEntity();
        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    @Order(7)
    void shouldDeleteProblem(){
        ResponseEntity<Void> response = this.restClient.delete()
                .uri(BASE_ROUTE + "/1")
                .retrieve()
                .toBodilessEntity();
        assertEquals(204, response.getStatusCode().value());
    }
}