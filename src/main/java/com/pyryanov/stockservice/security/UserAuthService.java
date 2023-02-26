package com.pyryanov.stockservice.security;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .map(user -> new User(
                        user.getUserName(),
                        user.getPassword(),
                        user.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toList())))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    public boolean saveUser(UserPrincipal user) {
//        UserPrincipal userFromDB = userRepository.findByUsername(user.getUserName()).get();
//
//        if (userFromDB != null) {
//            return false;
//        }
//
//        Role role = new Role();
//        role.setName("ROLE_USER");
//        user.setRoles(Collections.singleton(role));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
}
