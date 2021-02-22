package codeupspring.springblog.repositories;

import codeupspring.springblog.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

}
