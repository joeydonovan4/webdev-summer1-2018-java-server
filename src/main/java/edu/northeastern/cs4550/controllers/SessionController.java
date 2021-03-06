package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import edu.northeastern.cs4550.models.User;
import edu.northeastern.cs4550.services.IUserService;

@RestController
@RequestMapping("/api/session")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SessionController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User user, HttpSession session)
            throws FailedLoginException{
        User existingUser =
                userService.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existingUser == null) {
            throw new FailedLoginException("Incorrect password.");
        }
        session.setAttribute("user", existingUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out.");
    }

    @GetMapping
    public ResponseEntity<User> getLoggedInUser(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(currentUser);
    }
}
