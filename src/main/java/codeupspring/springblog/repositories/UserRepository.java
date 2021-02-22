package codeupspring.springblog.repositories;

import codeupspring.springblog.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
