package com.patagonialabs.juntadevecinos.repositories;

import com.patagonialabs.juntadevecinos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
