package com.example.WebCrawler.Repositories;
import com.example.WebCrawler.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long> {
    User getUserByUsername(@Param("username") String username);

}
