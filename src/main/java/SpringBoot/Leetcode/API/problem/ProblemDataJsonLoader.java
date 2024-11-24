package SpringBoot.Leetcode.API.problem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ProblemDataJsonLoader implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ProblemDataJsonLoader.class);
    ProblemRepository problemRepository;
    ObjectMapper objectMapper;

    public ProblemDataJsonLoader(ProblemRepository problemRepository, ObjectMapper objectMapper){
        this.problemRepository = problemRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) {
        if(this.problemRepository.count()==0){
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/problems.json")){
                Problems allProblems = objectMapper.readValue(inputStream,Problems.class);
                logger.info("Saving {} problems into database.", allProblems.problems().size());
                this.problemRepository.saveAll(allProblems.problems());
            }catch(IOException e){
                throw new RuntimeException("Failed to load problems data from json.");
            }
        } else{
            logger.info("Did not load problems data since problem repository has data.");
        }
    }
}
