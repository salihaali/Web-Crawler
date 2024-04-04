package com.example.WebCrawler.Repositories;
import com.example.WebCrawler.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);

}
