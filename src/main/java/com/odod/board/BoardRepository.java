package com.odod.board;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.odod.user.User;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
  Optional<User> findByEmail(String email);
}
