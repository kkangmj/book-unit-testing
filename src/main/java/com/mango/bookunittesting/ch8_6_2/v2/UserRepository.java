package com.mango.bookunittesting.ch8_6_2.v2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository8_6_2_v2")
public interface UserRepository extends JpaRepository<User, Integer> {
}
