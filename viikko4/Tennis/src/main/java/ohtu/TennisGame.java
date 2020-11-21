package ohtu;

public class TennisGame {

    private int p1_score = 0;
    private int p2_score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            p1_score++;
        } else if (playerName.equals(player2Name)) {
            p2_score++;
        }
    }

    private String getScoreName(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        return null;
    }

    private void draw(StringBuilder score) {
        if (p1_score >= 4) {
            score.append("Deuce");
        } else {
            score.append(getScoreName(p1_score) + "-All");
        }
    }

    private void winOrAdvance(StringBuilder score) {
        int minusResult = p1_score - p2_score;
        if (minusResult == 1) {
            score.append("Advantage " + player1Name);
        } else if (minusResult == -1) {
            score.append("Advantage " + player2Name);
        } else if (minusResult >= 2) {
            score.append("Win for " + player1Name);
        } else {
            score.append("Win for " + player2Name);
        }
    }

    public String getScore() {
        StringBuilder score = new StringBuilder();

        if (p1_score == p2_score) {
            draw(score);
        } else if (p1_score >= 4 || p2_score >= 4) {
            winOrAdvance(score);
        } else {
            score.append(getScoreName(p1_score) + "-" + getScoreName(p2_score));
        }
        return score.toString();
    }

}