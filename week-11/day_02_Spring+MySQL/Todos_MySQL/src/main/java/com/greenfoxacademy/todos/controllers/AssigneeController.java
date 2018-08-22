package com.greenfoxacademy.todos.controllers;

import com.greenfoxacademy.todos.models.Assignee;
import com.greenfoxacademy.todos.services.AssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/assignee")
@Controller
public class AssigneeController {

    private final
    AssigneeService assigneeService;

    @Autowired
    public AssigneeController(AssigneeService assigneeService) {
        this.assigneeService = assigneeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getListOfAssignees(Model model) {
        model.addAttribute("allAssignees", assigneeService.getAllAssignees());
        return "listOfAssignees";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "addAssignee";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAssignee(@ModelAttribute("name") String name,
                           @ModelAttribute("email") String email) {
        assigneeService.save(name, email);
        return "redirect:";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchAssignee(Model model, @ModelAttribute("name") String name) {
        if (assigneeService.getAssigneeByName(name).size() == 0) {
            model.addAttribute("errorflag", true); }
        model.addAttribute("searchedAssignees", assigneeService.getAssigneeByName(name));
        return "assigneeSearchResults";
    }

    @GetMapping(value = {"/delete"})
    public String deleteAssignee(@RequestParam("id") Long id) {
        assigneeService.deleteTodo(id);
        return "redirect:";
    }

    @GetMapping(value = {"/edit"})
    public String editFormPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("id", id);
        model.addAttribute("editedAssignee", new Assignee());
        return "editAssignee";
    }

    @PostMapping(value = {"/edit"})
    public String updateAssignee(@ModelAttribute(value = "editedAssignee") Assignee editedAssignee,
                         @ModelAttribute(value="id") Long id) {
        editedAssignee.setId(id);
        assigneeService.update(editedAssignee);
        return "redirect:";
    }

    @GetMapping(value = {"/todolist"})
    public String todosOfTheAssignee(Model model, @RequestParam("id") Long id) {
        System.out.println(assigneeService.getAssigneeById(id).getTodosOfTheAssignee());
        model.addAttribute("todolist", assigneeService.getAssigneeById(id).getTodosOfTheAssignee());
        return "assigneestodolist";
    }
}
