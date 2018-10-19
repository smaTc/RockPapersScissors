public class Rule {
    private Picks winner;
    private Picks loser;
    public Rule(Picks winner, Picks loser){
        this.winner = winner;
        this.loser = loser;
    }

    Picks getWinner(Picks obj1, Picks obj2){
        if( (obj1==this.winner && obj2==this.loser) ||(obj1==this.loser && obj2==this.winner)){
            return this.winner;
        }
        else return null;
    }
}
