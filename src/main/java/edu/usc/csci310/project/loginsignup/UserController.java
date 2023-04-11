package edu.usc.csci310.project.loginsignup;

import edu.usc.csci310.project.models.User;
import edu.usc.csci310.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;

import static edu.usc.csci310.project.Encryption.encrypt;

@RestController
@RequestMapping("/userController")
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @PostMapping("/signup")
    public String signUp(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            String responseString = "{\"success\": \"" + "failure" + "\"," +
                    "\"passwordmatch\": \"" + "false" + "\"}";
            return responseString;
        }
        if(password.length() < 8) {
            String responseString = "{\"success\": \"" + "failure" + "\"," +
                    "\"passwordmatch\": \"" + "true" + "\"," +
                    "\"passwordLength\": \"" + "false" + "\"}";
            return responseString;
        }
        else {
            //encrypting
            String en_name = encrypt(name, 5);
            String en_password = encrypt(password,5 );
            String en_email = encrypt(email, 5);

            // long emailid = Long.parseLong(en_email);
            if(userRepository.findByEmail(en_email) != null)
            {
                String responseString = "{\"success\": \"" + "failure" + "\"," +
                        "\"passwordmatch\": \"" + "true" + "\"," +
                        "\"userexists\": \"" + "false" + "\"," +
                        "\"passwordLength\": \"" + "true" + "\"}";
                return responseString;
            }

            User user = new User(en_name, en_email, en_password);
            userRepository.save(user);
            String responseString = "{\"success\": \"" + "true" + "\"}";
            return responseString;
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        String en_email = encrypt(email, 5);
        User user = userRepository.findByEmail(en_email);

        if (user == null) {
            String responseString = "{\"success\": \"" + "failure" + "\"," +
                    "\"loginuserexists\": \"" + "false" + "\"}";
            return responseString;
        }
        String en_password = encrypt(password, 5);

        if (!user.getPassword().equals(en_password)) {
            String responseString = "{\"success\": \"" + "failure" + "\"," +
                    "\"loginpasswordmatch\":\"" + "false" + "\"," +
                    "\"loginuserexists\": \"" + "true" + "\"}";
            return responseString;
        }
        String responseString = "{\"success\": \"" + "true" + "\"}";
        return responseString;
    }
}