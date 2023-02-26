package com.pyryanov.stockservice.security;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPrincipal, Long> {
    Optional<UserPrincipal> findByUserName(String userName);
}
