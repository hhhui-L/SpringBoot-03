package com.lgh.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewTestController {

    @GetMapping("/hellotest")
    public String hellotest(Model model){

        // model中的数据会被放在请求域中  request.setAttribute("a",aaa)
        model.addAttribute("msg","你好，世界");
        model.addAttribute("link","https://www.baidu.com/");
        return "success";
    }
}
