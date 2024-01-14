package com.mango.bookunittesting.ch7_4_2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository7_4_2")
public interface UserRepository extends JpaRepository<User, Integer> {
}
