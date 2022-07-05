package com.example.springvotingSystem.Controller;

import com.example.springvotingSystem.Entity.Candidates;
import com.example.springvotingSystem.Entity.User;
import com.example.springvotingSystem.Repository.CandidatesRepository;
import com.example.springvotingSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CandidatesRepository candidatesRepository;
    @GetMapping
    public String getHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user",new User());
        return "signup_form";
    }
    @PostMapping("process_register")
    public String processSignUpForm(User user){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassWord=encoder.encode(user.getPassword());
        user.setPassword(encodedPassWord);
        userRepository.save(user);
        return "register_success";
    }
    @GetMapping("/candidates")
    public String showVotingPage(Model model){
        model.addAttribute("candidates",new Candidates());
        return "voting_page";
    }

    @RequestMapping(value = "/voteSuccess", method= RequestMethod.POST)
    public String saveCandidate(Candidates candidate) {
        System.out.println("register candidate: " + candidate);
        candidatesRepository.save(candidate);
        return "voting_success";
     }
    @GetMapping("/list_users")
    public String shoListUsers(Model model){
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "list_users";
    }
    @GetMapping("/list_candidates")
    public String showListCandidate(Model model){
        List<Candidates> candidates = candidatesRepository.findAll();
        model.addAttribute("candidates", candidates);
        return "list_candidates";
    }
    @GetMapping("/admin_page")
    public String showAdminPage(Model model){
        return "admin_page";
    }
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long Id){
       userRepository.deleteById(Id);
        return "admin_page";
    }
    @GetMapping("/total-users")
    public String showTotal(Model model){

        model.addAttribute("countUsers", userRepository.count());

        return "total";
    }

}
