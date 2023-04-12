package edu.usc.csci310.project;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

// 973534
class PictureMontageControllerTest {

    PictureMontageController pmc = new PictureMontageController();
    @Test
    void pictureMontage() throws Exception {
        pmc.setMovies(new ArrayList<>(Arrays.asList("646385", "493922", "49018", "27205",
                "11", "181808", "1895", "24428", "299536", "1885")));
        ArrayList<String> temp = new ArrayList<String>();
        temp.add("646385");
        temp.add("493922");
        temp.add("49018");
        temp.add("27205");
        temp.add("11");
        temp.add("181808");
        temp.add("1895");
        temp.add("24428");
        temp.add("299536");
        temp.add("1885");
        String returnedResponse = pmc.pictureMontage("user");

        ArrayList<String> images = new ArrayList<>(Arrays.asList(returnedResponse));
        assertNotNull(returnedResponse);
        String image = images.get(0).substring(1, images.get(0).length() - 1);
        String[] temp2 = image.split(",");

        int i = 0;
        for (i = 0; i < 10; i++) {
            String[] keyValue = temp2[i].split(":");
            assert (keyValue.length == 3);
            String key = keyValue[0];
            String domain = keyValue[1];
            assert (key.contains(temp.get(i)));
            assert (domain.contains("https"));
            i++;
        }
        assert(i == 10);
    }

    @Test
    void lessThan10Movies() throws Exception {
        ArrayList<String> movies = new ArrayList<>(Arrays.asList("646385"));

        pmc.setMovies(movies);
        String returnedResponse = pmc.pictureMontage("user");
        ArrayList<String> images = new ArrayList<>(Arrays.asList(returnedResponse));

        assert (movies.get(0) == pmc.getMovies().get(0));
        // format "user id: img_link"
        String image = images.get(0).substring(1, images.get(0).length() - 1);
        String[] temp = image.split(",");

        int i = 0;
        for (i = 0; i < 10; i++) {
            String[] keyValue = temp[i].split(":");
            assert (keyValue.length == 3);
            String key = keyValue[0];
            String domain = keyValue[1];
            assert (key.contains(movies.get(0)));
            assert (domain.contains("https"));
            i++;
        }
        assert (i == 10);
    }
}