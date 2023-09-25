package com.miniproject.fisrtexportproject;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class controller {


    //@RequestMapping(value = "/register", method = RequestMethod.GET)
    @RequestMapping("/register")
    public String showForm(Model model) {
        Export2Office_Driver Export2Office_Driver = new Export2Office_Driver();
        model.addAttribute("Export2Office_Driver", Export2Office_Driver);
        return "nhapQuery";
    }


    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("Export2Office_Driver") Export2Office_Driver user) throws Exception {
        user.run();
        return "success";
    }
}