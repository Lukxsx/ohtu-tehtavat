package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        ArrayList<Player> suomalaiset = new ArrayList<>();
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                suomalaiset.add(player);
            }
        }
        LocalDateTime nyt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        Collections.sort(suomalaiset);
        System.out.println("Players from FIN " + formatter.format(nyt));
        for (Player player : suomalaiset) {
            System.out.format("%-22s%-8s%-2d + %-2d = %-2d\n", player.getName(), player.getTeam(), player.getGoals(), player.getAssists(), player.getPisteet());
        }
    }
}
