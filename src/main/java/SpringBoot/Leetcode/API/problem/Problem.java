package SpringBoot.Leetcode.API.problem;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record Problem(
    Integer id,
    @NotEmpty
    String name,
    String link,
    ProblemType type,
    LocalDateTime dateSolved,
    String notes
) {

}
