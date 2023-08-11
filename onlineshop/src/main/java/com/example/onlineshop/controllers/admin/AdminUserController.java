package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.dtos.AdminUserListDto;
import com.example.onlineshop.dtos.UserDto;
import com.example.onlineshop.models.AuthUser;
import com.example.onlineshop.models.User;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.security.AdminRequired;
import com.example.onlineshop.services.GetUserListService;
import com.example.onlineshop.services.GetUserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AdminRequired
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
  private final GetUserListService getUserListService;
  private final GetUserService getUserService;

  public AdminUserController(GetUserListService getUserListService,
                             GetUserService getUserService) {
    this.getUserListService = getUserListService;
    this.getUserService = getUserService;
  }

  @GetMapping("/me")
  public UserDto me(Authentication authentication) {
    AuthUser authUser = (AuthUser) authentication.getPrincipal();
    UserId id = new UserId(authUser.id());
    User user = getUserService.getAdminUser(id);
    return UserDto.of(user);
  }

  @GetMapping
  public AdminUserListDto list() {
    List<User> users = getUserListService.getUserList();
    return AdminUserListDto.of(users);
  }
}