package com.sekolah.websekolah.jwt;


import com.sekolah.websekolah.entity.User;
import com.sekolah.websekolah.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Optional<User> user = userRepository.findByEmail(email);
            return user.map(CustomUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("email not found " + email));
        }
}
