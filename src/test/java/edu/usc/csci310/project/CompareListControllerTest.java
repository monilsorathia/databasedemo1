package edu.usc.csci310.project;

import edu.usc.csci310.project.models.User;
import edu.usc.csci310.project.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;

import static edu.usc.csci310.project.Encryption.encrypt;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompareListControllerTest {

    @Test
    void getListNamesFirst() {
        CompareListController controller = new CompareListController();
        UserRepository mockrepo = mock(UserRepository.class);
        controller.userRepository = mockrepo;

        when(mockrepo.findByEmail(anyString())).thenReturn(null);
        String expected = "[]";
        assertEquals(expected, controller.getListNames("usernotfound"));
    }

    @Test
    void getListNamesSecond() {
        CompareListController controller = new CompareListController();
        UserRepository mockrepo = mock(UserRepository.class);
        controller.userRepository = mockrepo;
        User mockuser = mock(User.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(null);

        String expected = "[]";
        assertEquals(expected, controller.getListNames("listnotfound"));
    }

    @Test
    void getListNameThird() {
        CompareListController controller = new CompareListController();
        UserRepository mockrepo = mock(UserRepository.class);
        controller.userRepository = mockrepo;
        User mockuser = mock(User.class);
        Hashtable<String, ArrayList<String>> temptable = new Hashtable<String, ArrayList<String>>();
        ArrayList<String> templist = new ArrayList<String>();
        templist.add(encrypt("movie1", 5));
        temptable.put(encrypt("list1", 5), templist);
        temptable.put(encrypt("list2", 5), templist);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(temptable);

        String expected = "[list1, list2]";
        assertEquals(expected, controller.getListNames("normalcase"));
    }

    @Test
    void getMovieNamesFirst() {
        CompareListController controller = new CompareListController();
        UserRepository mockrepo = mock(UserRepository.class);
        controller.userRepository = mockrepo;
        when(mockrepo.findByEmail(anyString())).thenReturn(null);
        String expected = "[]";
        assertEquals(expected, controller.getMovieNames("usernotfound", ""));
    }

    @Test
    void getMovieNamesSecond() {
        CompareListController controller = new CompareListController();
        UserRepository mockrepo = mock(UserRepository.class);
        controller.userRepository = mockrepo;
        User mockuser = mock(User.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(null);

        String expected = "[]";
        assertEquals(expected, controller.getMovieNames("userfound", "listnotfound"));
    }

    @Test
    void getMovieNamesThird() {
        CompareListController controller = new CompareListController();
        UserRepository mockrepo = mock(UserRepository.class);
        controller.userRepository = mockrepo;
        User mockuser = mock(User.class);
        Hashtable<String, ArrayList<String>> mocktable = mock(Hashtable.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mocktable);
        when(mocktable.get(anyString())).thenReturn(null);

        String expected = "[]";
        assertEquals(expected, controller.getMovieNames("userfound", "moviesnotfound"));
    }

    @Test
    void getMovieNamesFourth()
    {
        CompareListController controller = new CompareListController();
        UserRepository mockrepo = mock(UserRepository.class);
        controller.userRepository = mockrepo;
        User mockuser = mock(User.class);
        Hashtable<String, ArrayList<String>> mocktable = mock(Hashtable.class);
        ArrayList<String> templist = new ArrayList<String>();
        templist.add(encrypt("movie1", 5));
        templist.add(encrypt("movie2", 5));

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mocktable);
        when(mocktable.get(anyString())).thenReturn(templist);

        String expected = "[movie1, movie2]";
        assertEquals(expected, controller.getMovieNames("userfound", "moviesfound"));
    }
}