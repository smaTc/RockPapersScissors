public class Rule {
    private String winner;
    private String loser;
    public Rule(String winner, String loser){
        this.winner = winner;
        this.loser = loser;
    }

    String getWinner(String obj1, String obj2){
        if( (obj1.equals(winner) && obj2.equals(loser)) ||(obj1.equals(loser) && obj2.equals(winner))){
            return this.winner;
        }
        else return "";
    }
}
