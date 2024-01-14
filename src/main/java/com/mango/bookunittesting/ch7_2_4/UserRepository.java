package com.mango.bookunittesting.ch7_2_4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository7_2_4")
public interface UserRepository extends JpaRepository<User, Integer> {
}
