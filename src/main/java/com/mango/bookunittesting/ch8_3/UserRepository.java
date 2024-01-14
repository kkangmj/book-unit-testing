package com.mango.bookunittesting.ch8_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository8_3")
public interface UserRepository extends JpaRepository<User, Integer> {
}
