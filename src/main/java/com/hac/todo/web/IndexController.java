package com.hac.todo.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public ModelAndView login(ModelAndView mv) throws Exception{
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/logout2")
    public ModelAndView logout(ModelAndView mv){
        mv.setViewName("login");
        return mv;
    }

}
