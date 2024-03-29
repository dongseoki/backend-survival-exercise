package com.example.onlineshop.services;

import com.example.onlineshop.daos.AuthUserDao;
import com.example.onlineshop.exceptions.EmailAlreadyTaken;
import com.example.onlineshop.models.User;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.UserRepository;
import com.example.onlineshop.security.AccessTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.onlineshop.models.Role.ROLE_USER;

@Service
@Transactional
public class SignupService {
  private final AuthUserDao authUserDao;

  private final PasswordEncoder passwordEncoder;

  private final AccessTokenGenerator accessTokenGenerator;

  private final UserRepository userRepository;

  public SignupService(AuthUserDao authUserDao,
                       PasswordEncoder passwordEncoder,
                       AccessTokenGenerator accessTokenGenerator,
                       UserRepository userRepository) {
    this.authUserDao = authUserDao;
    this.passwordEncoder = passwordEncoder;
    this.accessTokenGenerator = accessTokenGenerator;
    this.userRepository = userRepository;
  }

  public String signup(String email, String name, String password) {
    if (userRepository.existsByEmail(email)) {
      throw new EmailAlreadyTaken(email);
    }

    UserId userId = createUser(email, name, password);

    return createAccessToken(userId);
  }

  private UserId createUser(String email, String name, String password) {
    UserId userId = UserId.generate();

    User user = new User(userId, email, name, ROLE_USER);
    user.changePassword(password, passwordEncoder);

    userRepository.save(user);

    return userId;
  }

  private String createAccessToken(UserId userId) {
    String accessToken = accessTokenGenerator.generate(userId.toString());

    authUserDao.addAccessToken(userId.toString(), accessToken);

    return accessToken;
  }
}
