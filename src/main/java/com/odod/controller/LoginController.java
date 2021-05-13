package com.odod.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odod.security.SessionUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {

  private final HttpSession httpSession;

  @ApiOperation(value = "기본 Demo 페이지", tags = "OAuth2 로그인 관리")
  @RequestMapping(value = "/")
  public String loginPage(Model model) {
    SessionUser user = (SessionUser) httpSession.getAttribute("user");

    if (user != null) {
      model.addAttribute("userName", user.getName());
      model.addAttribute("picture", user.getPicture());
    }

    return "thymeleaf/login";
  }

  @ApiOperation(value = "OAuth2 로그아웃", tags = "OAuth2 로그인 관리")
  @RequestMapping(value = "/logout")
  public String logout(Model model) {
    SessionUser user = (SessionUser) httpSession.getAttribute("user");

    if (user != null)
      model.addAttribute("userName", user.getName());

    return "thymeleaf/login";
  }
}
