package com.sitejava.sitetrocaqui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;
import com.sitejava.sitetrocaqui.model.Task;

@Controller
public class TaskController {

    List<Task> tasks = new ArrayList<>();

    @GetMapping("/create")
    public String home() {
        return "create";
    }

    @PostMapping("/create")
    public String create(Task task) {
        Long id = tasks.size() + 1L;
        tasks.add(new Task(id, task.getName(), task.getDate()));

        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("tasks", tasks);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("create");

        Task taskFind = tasks.stream().filter(task -> id.equals(task.getId())).findFirst().get();
        mv.addObject("task", taskFind);
        return mv;
    }
}
