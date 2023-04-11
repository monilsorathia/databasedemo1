package edu.usc.csci310.project.watchlist;

import edu.usc.csci310.project.models.User;
import edu.usc.csci310.project.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.*;

import static edu.usc.csci310.project.Encryption.decrypt;
import static edu.usc.csci310.project.Encryption.encrypt;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class watchlistControllerTest {

    /*@Test
    public void createListFirst()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;

        when(mockrepo.findByEmail(anyString())).thenReturn(null);
        String answer = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(answer, wc.createList("temp", "temp"));
    }

    @Test
    public void createListSecond()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;

        User mockuser = mock(User.class);

        ArrayList<String> templist = new ArrayList<String>();
        templist.add(encrypt("random", 5));

        Hashtable<String, ArrayList<String>> temptable = new Hashtable<String, ArrayList<String>>();
        temptable.put(encrypt("random", 5), templist);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(temptable);

        String expected = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.createList("random", "temp"));
    }

    @Test
    public void createListThird()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(null);

        doNothing().when(mockuser).createnewList(anyString());
        when(mockuser.addtowatchlist(anyString(), anyString())).thenReturn(true);

        String expected = "{\"success\": \"" + "true" + "\"," +
                "\"failure\": \"" + "false" + "\"}";
        assertEquals(expected, wc.createList("temp", "temp"));
    }

    @Test
    public void addtoListFirst()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;

        when(mockrepo.findByEmail(anyString())).thenReturn(null);

        String expected = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void addtoListSecond()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(null);

        String expected = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void addtoListThird()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        Hashtable<String, ArrayList<String>> mockhashmap = mock(Hashtable.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.get(anyString())).thenReturn(new ArrayList<String>());
        when(mockuser.addtowatchlist(anyString(), anyString())).thenReturn(true);

        String expected = "{\"success\": \"" + "true" + "\"," +
                "\"failure\": \"" + "false" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void addtoListFourth()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        Hashtable<String, ArrayList<String>> mockhashmap = mock(Hashtable.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.get(anyString())).thenReturn(new ArrayList<String>());
        when(mockuser.addtowatchlist(anyString(), anyString())).thenReturn(false);

        String expected = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void addtoListFifth()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        Hashtable<String, ArrayList<String>> mockhashmap = mock(Hashtable.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.get(anyString())).thenReturn(null);

        String expected = "{\"success\": \"" + "false" + "\"," +
                "\"failure\": \"" + "true" + "\"}";
        assertEquals(expected, wc.addtolist("temp", "temp", "temp"));
    }

    @Test
    public void showallListsFirst()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;

        when(mockrepo.findByEmail(anyString())).thenReturn(null);
        String expected = "[]";
        assertEquals(expected, wc.retrieveList("temp", "temp"));
    }

    @Test
    public void showallListsSecond()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(null);

        String expected = "[]";
        assertEquals(expected, wc.retrieveList("temp", "temp"));
    }

    @Test
    public void showallListsThird()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        Hashtable<String, ArrayList<String>> mockhashmap = mock(Hashtable.class);

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.isEmpty()).thenReturn(true);

        String expected = "[]";
        assertEquals(expected, wc.retrieveList("temp", "temp"));
    }

    @Test
    public void showallListsFourth()
    {
        watchlistController wc = new watchlistController();
        UserRepository mockrepo = mock(UserRepository.class);
        wc.userRepository = mockrepo;
        User mockuser = mock(User.class);
        Hashtable<String, ArrayList<String>> mockhashmap = mock(Hashtable.class);
        Set<String> tempset = new HashSet<String>();
        tempset.add("first");
        tempset.add("second");

        when(mockrepo.findByEmail(anyString())).thenReturn(mockuser);
        when(mockuser.getAllWatchlists()).thenReturn(mockhashmap);
        when(mockhashmap.isEmpty()).thenReturn(false);
        when(mockhashmap.keySet()).thenReturn(tempset);

        String decryptfirst = decrypt("first");
        String decrppysecond = decrypt("second");

        String expected = "[ " + decryptfirst + ", " + decrppysecond + "]";
        assertEquals(expected, wc.retrieveList("temp", "temp"));
    }*/
}