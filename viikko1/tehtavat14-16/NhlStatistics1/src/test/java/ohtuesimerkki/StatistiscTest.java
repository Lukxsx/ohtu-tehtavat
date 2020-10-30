/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luks
 */
public class StatistiscTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54)); // 99
            players.add(new Player("Kurri",   "EDM", 37, 53)); // 90
            players.add(new Player("Yzerman", "DET", 42, 56)); // 98
            players.add(new Player("Gretzky", "EDM", 35, 89)); // 124
 
            return players;
        }
    };
    
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  

    @Test
    public void searchTest1() {
        assertEquals(stats.search("asd"), null);
    }
    
    @Test
    public void searchTest2() {
        Player testplayer = stats.search("Kurri");
        assertEquals(testplayer.getName(), "Kurri");
    }
    
    @Test
    public void teamMembersTest() {
        List<Player> testTeam = stats.team("EDM");
        assertEquals(testTeam.size(), 3);
    }
    
    @Test
    public void topScorersTest1() {
        List<Player> top = stats.topScorers(4);
        assertEquals(top.get(0).getName(), "Gretzky");
        assertEquals(top.get(1).getName(), "Lemieux");
        assertEquals(top.get(2).getName(), "Yzerman");
        assertEquals(top.get(3).getName(), "Kurri");
        assertEquals(top.get(4).getName(), "Semenko");
    }
    
}
