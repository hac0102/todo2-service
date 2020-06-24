package com.hac.todo.web;

import com.hac.todo.config.auth.LoginUser;
import com.hac.todo.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@Slf4j
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public ModelAndView login(ModelAndView mv, @LoginUser SessionUser user ) throws Exception{

        if(user == null){
            mv.setViewName("login");
            return mv;
        }

        mv.addObject("userInfo", user);
        mv.setViewName("main");

        return mv;
    }

    @GetMapping("/logout2")
    public ModelAndView logout(ModelAndView mv, @LoginUser SessionUser user){
        log.info("로그아웃 name :: {}", user == null ? "null" : user.getName());
        log.info("로그아웃 email :: {}", user == null ? "null" : user.getEmail());
        mv.setViewName("login");
        return mv;
    }

}
