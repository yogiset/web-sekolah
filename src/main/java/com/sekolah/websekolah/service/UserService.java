package com.sekolah.websekolah.service;

import com.google.common.base.Strings;
import com.sekolah.websekolah.constant.ApiConstant;
import com.sekolah.websekolah.entity.Staff;
import com.sekolah.websekolah.entity.User;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.jwt.JwtUtil;
import com.sekolah.websekolah.repository.UserRepository;
import com.sekolah.websekolah.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final MailService mailService;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> createAccount(Map<String, String> requestMap) {
        log.info("Inside Create Account",requestMap);

        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole)) {

            try {

                if (!validateCreateAccountMap(requestMap)) {
                    return UserUtils.getResponseEntity(ApiConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }

                String email = requestMap.get("email");
                if (userRepository.existsByEmail(email)) {
                    return UserUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }

                String name = requestMap.get("name");
                Optional<User> userOptional = userRepository.findByName(name);
                if (userOptional.isPresent()) {
                    return UserUtils.getResponseEntity("Username already exists", HttpStatus.BAD_REQUEST);
                }

                User user = createUserFromMap(requestMap);
                return UserUtils.getResponseEntity("Successfully Create Account !", HttpStatus.OK);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }    return UserUtils.getResponseEntity("Invalid user role", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateCreateAccountMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("no_telp")
                && requestMap.containsKey("email") && requestMap.containsKey("role")
                && requestMap.containsKey("password");
    }

    private User createUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setNo_telp(requestMap.get("no_telp"));
        user.setEmail(requestMap.get("email"));
        user.setRole(requestMap.get("role"));
        String rawPassword = requestMap.get("password");
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        user.setStatus("true");
        user.setCreated(Instant.now());
        return userRepository.save(user);
    }

    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside Login");
        try {
            String email = requestMap.get("email");
            String password = requestMap.get("password");


            Optional<User> userOpt1 = userRepository.findByEmail(email);
            if (!userOpt1.isPresent()) {
                return new ResponseEntity<>("{\"message\":\"Our System didn't find your email, Please Register first !\"}", HttpStatus.BAD_REQUEST);
            }
            // Retrieve the user by email from the repository
            User user = userRepository.findByEmailId(email);
            if (user != null) {
                // Check if the provided password matches the stored password
                if (passwordEncoder.matches(password, user.getPassword())) {
                    if ("true".equalsIgnoreCase(user.getStatus())) {
                        // Generate the JWT token for the authenticated user
                        String jwtToken = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getName());

                        // Return the token as a JSON response
                        return new ResponseEntity<>("{\"token\":\"" + jwtToken + "\"}", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("{\"message\":\"Your Account has been locked,please ask owner !\"}", HttpStatus.BAD_REQUEST);
                    }
                }
            }

        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<>("{\"message\":\"Bad Credentials. Please check your password or email\"}", HttpStatus.BAD_REQUEST);
    }

    public List<User> listUser(Map<String,String> requestMap) throws AllException {
        log.info("Inside listUser");
            return userRepository.findAll();

    }

    public List<User> showAllUserByAsccending(String field) {
        log.info("Inside showAllUserByAsccending");
        return userRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public List<User> showAllUserByDescending(String field) {
        log.info("Inside showAllUserByDescending");
        return userRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    public ResponseEntity<String> changePassword(Map<String, String> requestMap, String userEmail) {
        try {
            log.info("Inside changePassword");

            userEmail = requestMap.get("email");

            if (userEmail != null) {
                log.info("Current Email: " + userEmail);

                // Retrieve the user from the database
                User user = userRepository.findByEmailId(userEmail);

                if (user != null) {
                    String oldPassword = requestMap.get("oldPassword");
                    String newPassword = requestMap.get("newPassword");

                    // Encode the old password using the same password encoder
                    String encodedOldPassword = passwordEncoder.encode(oldPassword);

                    // Compare the encoded old password with the stored hashed password
                    if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                        // Encode the new password before saving it
                        String encodedNewPassword = passwordEncoder.encode(newPassword);

                        // Update the user's password with the new hashed password
                        user.setPassword(encodedNewPassword);
                        userRepository.save(user);

                        // Password updated successfully
                        return UserUtils.getResponseEntity("Password Updated Successfully", HttpStatus.OK);
                    } else {
                        // Incorrect old password
                        return UserUtils.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
                    }
                }

                return UserUtils.getResponseEntity("User not found", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                log.error("Token is null or empty");
                return UserUtils.getResponseEntity("Token is null or empty", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            // Log the exception for debugging purposes
            log.error("An error occurred while changing the password", ex);

            // Handle the exception more gracefully and provide a meaningful error message
            return UserUtils.getResponseEntity("An error occurred while changing the password.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        log.info("Inside forgotPassword");


        try {
            User user = userRepository.findByEmailId(requestMap.get("email"));
            if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) {
                // Generate a new random password (you can customize this part)
                String newPassword = generateRandomPassword(); // Implement this method

                // Hash the new password with BCryptPasswordEncoder
                String hashedPassword = passwordEncoder.encode(newPassword);

                // Update the user's password in the database with the new hash
                user.setPassword(hashedPassword);
                userRepository.save(user);

                // Send the new password via email
                mailService.forgotMail(user.getEmail(), "New Password for you", newPassword);

                return UserUtils.getResponseEntity("Check your mail for the new password", HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UserUtils.getResponseEntity("Email did not exists", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 12;

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }

    public User fetchUserByName(String name,Map<String, String> requestMap) throws AllException {
            Optional<User> user = userRepository.findByName(name);
            if (!user.isPresent()) {
                throw new AllException("User tidak ditemukan");
            }
            return user.get();
    }

    public void deleteUserById(Long id,Map<String, String> requestMap) throws AllException {
        String userRole = requestMap.get("role");

        if ("owner".equalsIgnoreCase(userRole)) {
        boolean exist = userRepository.existsById(id);
        if (!exist) {
            throw new AllException("User dengan Id" + id + "tidak ada");
        }
        userRepository.deleteById(id);
        } throw new AllException("Invalid user role");
    }


    public User updateUser(Long id, User user,Map<String, String> requestMap) throws AllException {
        String userRole = requestMap.get("role");

        if ("owner".equalsIgnoreCase(userRole)) {

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new AllException("Nama harus di isi !!!");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new AllException("Email harus di isi !!!");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new AllException("Password harus di isi !!!");
        }
        if (user.getNo_telp() == null || user.getNo_telp().isEmpty()) {
            throw new AllException("No HP harus di isi !!!");
        }


        User updatedUser = userRepository.findById(id)
                .orElseThrow(() -> new AllException("User dengan Id" + id + "tidak ada"));

        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setNo_telp(user.getNo_telp());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRole(user.getRole());
        updatedUser.setStatus(user.getStatus());
        userRepository.save(updatedUser);

        return updatedUser;
        } throw new AllException("Invalid user role");
    }
}
