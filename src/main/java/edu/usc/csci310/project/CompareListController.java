package edu.usc.csci310.project;

import edu.usc.csci310.project.models.User;
import edu.usc.csci310.project.loginsignup.UserController;
import edu.usc.csci310.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;

import static edu.usc.csci310.project.Encryption.decrypt;
import static edu.usc.csci310.project.Encryption.encrypt;

@RestController
@RequestMapping("/comparelistController")
public class CompareListController
{
    @Autowired
    public UserRepository userRepository;

    @PostMapping("/getlistnames")
    public String getListNames(@RequestParam String email)
    {
        String en_email = encrypt(email, 5);
        User user = userRepository.findByEmail(en_email);

        if (user != null) {
            Hashtable<String, ArrayList<String>> alllists = user.getAllWatchlists();
            if (alllists == null)
            {
                String responseString = "[]";
                return responseString;
            }
            else
            {
                String responseString = "[";
                String listnames = "";
                for(String key: alllists.keySet())
                {
                    listnames = decrypt(key);
                    responseString = responseString + listnames + ", ";
                }
                responseString = responseString.substring(0, responseString.length()-2) + "]";
                return responseString;
            }
        }
        String responseString = "[]";
        return responseString;
    }

    @PostMapping("/getmovies")
    public String getMovieNames(@RequestParam String email, @RequestParam String watchlistname)
    {
        String en_email = encrypt(email, 5);
        User user = userRepository.findByEmail(en_email);
        if(user != null)
        {
            String en_name = encrypt(watchlistname, 5);
            Hashtable<String, ArrayList<String>> alllists = user.getAllWatchlists();
            if(alllists == null)
            {
                return "[]";
            }

            ArrayList<String> specificlist = alllists.get(en_name);
            if(specificlist == null)
            {
                return "[]";
            }
            else
            {
                String id = "";
                String responseString = "[";
                for(int i = 0; i < specificlist.size(); i++)
                {
                    String encrypted_id = specificlist.get(i);
                    responseString = responseString + decrypt(encrypted_id) + ", ";
                }
                responseString = responseString.substring(0, responseString.length() - 2) + "]";
                return responseString;
            }

        }
        return "[]";
    }
}