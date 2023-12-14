package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String printAll(Model model) {
        model.addAttribute("users", service.getAll());
        return "index";
    }

    @GetMapping("/new")
    public String printAddForm(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String remove(@RequestParam("id") long id) {
        service.removeById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String printEditForm(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", service.getById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") User user, @RequestParam("id") long id) {
        service.update(user, id);
        return "redirect:/";
    }
}
