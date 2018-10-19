import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private String player;
    private Random rand;
    private ArrayList<Rule> rules = new ArrayList<>();
    private boolean started=false;
    private Picks[] picks;


    public void startGame(){
        setRules();
       this.picks = Picks.getPicks();
        rand = new Random();
        System.out.println("What is your name?");
        this.player = readInput();
        System.out.println("OK, "+this.player+". You can exit the Game anytime by typing \"exit\". Press Enter to play!\n\n");
        started=true;
        readInput();
        System.out.print("\033[H\033[2J");
        playGame();

    }

    public void setRules(){
        rules.add(new Rule(Picks.Rock,Picks.Scissors));
        rules.add(new Rule(Picks.Papers,Picks.Rock));
        rules.add(new Rule(Picks.Scissors,Picks.Papers));
        rules.add(new Rule(Picks.Rock,Picks.Lizard));
        rules.add(new Rule(Picks.Lizard,Picks.Spock));
        rules.add(new Rule(Picks.Spock,Picks.Scissors));
        rules.add(new Rule(Picks.Scissors,Picks.Lizard));
        rules.add(new Rule(Picks.Lizard,Picks.Papers));
        rules.add(new Rule(Picks.Papers,Picks.Spock));
        rules.add(new Rule(Picks.Spock,Picks.Rock));
    }

    private void playGame(){
        System.out.println("Choose a number:");
        for(int i=0;i<picks.length;i++){
            //String pick = picks[i].toString().split(".")[1];
            String pick = picks[i].toString();
            System.out.println((i+1)+". " + pick);
        }
        int playerPick=Integer.parseInt(readInput());
        int computerPick = rand.nextInt(picks.length)+1;
        getWinner(playerPick,computerPick);
        System.out.println("\n");
        System.out.println("Press Enter for another game or type \"exit\" to exit the Game.");
        String action = readInput();
        System.out.print("\033[H\033[2J");
        playGame();

    }

    private void getWinner(int player,int computer){
        Picks pick1_ = toItem(player);
        System.out.println("You chose: "+pick1_);
        Picks pick2_ = toItem(computer);
        System.out.println("Computer chose: "+pick2_);
        int winner=checkRules(pick1_,pick2_);
        if(winner==1){
            System.out.println(this.player+" wins!");
        }
        else if(winner==2){
            System.out.println("Computer wins!");
        }
        else if(winner==0){
            System.out.println("Draw!");
        }
        else{
            System.out.println("Wrong input...try again");
        }
    }

    private int checkRules(Picks player,Picks computer){
        if(player.equals(computer))return 0;
        Picks winner = null;
        for(int i=0;i<rules.size();i++){
            if(rules.get(i).getWinner(player,computer) != null){
                winner = rules.get(i).getWinner(player,computer);
            }
        }

        if(winner.equals(player))return 1;
        else if(winner.equals(computer))return 2;
        else return -1;
    }

    private Picks toItem(int pick){
        if(pick>0) return this.picks[pick-1];
        else return null;
    }

    private String readInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = null;
        try{
            input = reader.readLine();
            //System.out.println("input:"+input);

        }catch (IOException e){
            System.out.println("Wrong input...try again\n\n");
            readInput();
        }
        if(input.equals("exit"))System.exit(0);
        if(started){
            if(!input.equals("") && Integer.parseInt(input)>picks.length ){
                System.out.println("Wrong input...try again.");
                readInput();
            }

        }
        return input;
    }
}
