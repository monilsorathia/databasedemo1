package edu.usc.csci310.project.watchlist;

import edu.usc.csci310.project.models.User;
import edu.usc.csci310.project.loginsignup.UserController;
import edu.usc.csci310.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static edu.usc.csci310.project.Encryption.decrypt;
import static edu.usc.csci310.project.Encryption.encrypt;
import java.util.ArrayList;
import java.util.Hashtable;

@RestController
@RequestMapping("/watchlistController")
public class watchlistController {
    //public Hashtable<String, User> userdatabase = new Hashtable<String, User>();
    @Autowired
    public UserRepository userRepository;

    @PostMapping("/createwatchlist")
    public String createList(@RequestParam String watchlistname, @RequestParam String email) {
        //encrypting
        UserController userController = new UserController();
        userController.signUp("john", "dummywatchlist@usc.edu", "1234", "1234");
        String en_email = encrypt(email, 5);

        User user = userRepository.findByEmail(en_email);
        if (user != null) {
            String en_name = encrypt(watchlistname, 5);

            Hashtable<String, ArrayList<String>> alllists = user.getAllWatchlists();
            ArrayList<String> specificlist = alllists.get(en_name);
            System.out.println(en_name);
            if (specificlist == null) {
                user.createnewList(en_name);
                String responseString = "{\"success\": \"" + "true" + "\"," +
                        "\"failure\": \"" + "false" + "\"}";
                return responseString;
            } else {
                String responseString = "{\"success\": \"" + "false" + "\"," +
                        "\"failure\": \"" + "true" + "\"}";
                return responseString;
            }
        }
        String responseString = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        return responseString;
    }


    @PostMapping("/addtolist")
    public String addtolist(@RequestParam String email, @RequestParam String watchlistname, @RequestParam String id)
    {
        UserController userController = new UserController();
        userController.signUp("john", "dummywatchlist@usc.edu", "123456789", "123456789");
        //userController.userRepository = userRepository;
        String en_email = encrypt(email, 5);
        System.out.println(email + watchlistname + id);

        User user = userRepository.findByEmail(en_email);
        if(user != null)
        {
            System.out.println("Entered");
            String en_name = encrypt(watchlistname, 5);
            Hashtable<String, ArrayList<String>> alllists = user.getAllWatchlists();
            ArrayList<String> specificlist = alllists.get(en_name);
            if(specificlist == null)
            {
                String responseString = "{\"success\": \"" + "false" + "\"," +
                        "\"failure\": \"" + "true" + "\"}";
                System.out.println("Null");
                return responseString;
            }
            else {
                user.addtowatchlist(id, encrypt(watchlistname, 5));
                String responseString = "{\"success\": \"" + "true" + "\"," +
                        "\"failure\": \"" + "false" + "\"}";
                System.out.println("Not Null");
                return responseString;
            }
        }
        String responseString = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        return responseString;
    }
}

























