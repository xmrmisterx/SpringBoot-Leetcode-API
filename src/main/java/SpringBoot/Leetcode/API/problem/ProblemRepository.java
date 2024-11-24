package SpringBoot.Leetcode.API.problem;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class ProblemRepository {
    private final JdbcClient jdbcClient;

    public ProblemRepository(JdbcClient jdbcClient){
        this.jdbcClient=jdbcClient;
    }

    public int count(){
        return this.jdbcClient.sql("select * from problem").query().listOfRows().size();
    }

    public void create(Problem problem){
        var updated = this.jdbcClient.sql("insert into problem(id,name,link,type,date_solved,notes) values(?,?,?,?,?,?)")
                .params(List.of(problem.id(),problem.name(),problem.link(),problem.type().toString(),problem.dateSolved(),problem.notes()))
                .update();
        Assert.state(updated==1, "Failed to create problem with name: " + problem.name());
    }

    public void saveAll(List<Problem> problems){
        problems.stream().forEach(this::create);
    }

    public List<Problem> findAll(){
        return this.jdbcClient.sql("select * from problem")
                .query(Problem.class)
                .list();
    }

    public Optional<Problem> findById(int id){
        return this.jdbcClient.sql("select * from problem where id=:id")
                .param("id", id)
                .query(Problem.class)
                .optional();
    }

    public List<Problem> findByType(String type){
        return this.jdbcClient.sql("select * from problem where type=:type")
                .param("type",type)
                .query(Problem.class)
                .list();
    }

    public void update(Problem problem,int id){
        var updated = this.jdbcClient.sql("update problem set name=?,link=?,type=?,date_solved=?,notes=? where id=?")
                .params(List.of(problem.name(),problem.link(), problem.type().toString(),problem.dateSolved(),problem.notes(),id))
                .update();
        Assert.state(updated==1,"Failed to update problem with name: " + problem.name());
    }

    public void delete(int id){
        var updated = this.jdbcClient.sql("delete from problem where id=:id")
                .param("id", id)
                .update();
        Assert.state(updated==1,"Failed to delete problem with id: " + id);
    }
}
