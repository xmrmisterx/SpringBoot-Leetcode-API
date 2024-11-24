package SpringBoot.Leetcode.API.problem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import static SpringBoot.Leetcode.API.constants.Constants.BASE_ROUTE;

@RestController
@RequestMapping(BASE_ROUTE)
public class ProblemController {
    ProblemRepository problemRepository;

    public ProblemController(ProblemRepository problemRepository){
        this.problemRepository = problemRepository;
    }

    @GetMapping("")
    List<Problem> findAll(){
        return this.problemRepository.findAll();
    }

    @GetMapping("/id/{id}")
    Problem findById(@PathVariable int id){
        Optional<Problem> problem = this.problemRepository.findById(id);
        if(problem.isPresent()){
            return problem.get();
        }
        throw new ProblemNotFoundException();
    }

    @GetMapping("/type/{type}")
    List<Problem> getByType(@PathVariable String type){
        return this.problemRepository.findByType(type);
    }

    @GetMapping("/count")
    int count(){
        return this.problemRepository.count();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Problem problem){
        this.problemRepository.create(problem);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Problem problem, @PathVariable int id){
        this.problemRepository.update(problem,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        this.problemRepository.delete(id);
    }
}
