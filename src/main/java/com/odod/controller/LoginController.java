package com.odod.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odod.security.SessionUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {

  private final HttpSession httpSession;

  @RequestMapping(value = "/")
  public String loginPage(Model model) {
    SessionUser user = (SessionUser) httpSession.getAttribute("user");

    if (user != null) {
      model.addAttribute("userName", user.getName());
      model.addAttribute("picture", user.getPicture());
    }

    return "thymeleaf/login";
  }

  @RequestMapping(value = "/logout")
  public String logout(Model model) {
    SessionUser user = (SessionUser) httpSession.getAttribute("user");

    if (user != null)
      model.addAttribute("userName", user.getName());

    return "thymeleaf/login";
  }
}
