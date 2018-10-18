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


    public void startGame(){
        setRules();
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
        rules.add(new Rule("Rock","Scissors"));
        rules.add(new Rule("Papers","Rock"));
        rules.add(new Rule("Scissors","Papers"));
    }

    private void playGame(){
        System.out.println("Choose a number:");
        System.out.println("1. Rock");
        System.out.println("2. Papers");
        System.out.println("3. Scissors");
        System.out.println("Your pick: ");
        int playerPick=Integer.parseInt(readInput());
        int computerPick = rand.nextInt(3)+1;
        getWinner(playerPick,computerPick);
        //System.out.println("Play another round?");
        System.out.println("\n");
        System.out.println("Press Enter for another game or type \"exit\" to exit the Game.");
        String action = readInput();
        System.out.print("\033[H\033[2J");
        playGame();

    }

    private void getWinner(int player,int computer){
        String pick1_ = toItem(player);
        System.out.println("You chose: "+pick1_);
        String pick2_ = toItem(computer);
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

    private int checkRules(String player,String computer){
        if(player.equals(computer))return 0;
        /*
        else if(pick1.equals("Rock") && pick2.equals("Papers")) return 2;
        else if(pick2.equals("Rock") && pick1.equals("Papers")) return 1;
        else if(pick1.equals("Rock") && pick2.equals("Scissors")) return 1;
        else if(pick2.equals("Rock") && pick1.equals("Scissors")) return 2;
        else if(pick1.equals("Papers") && pick2.equals("Scissors")) return 2;
        else if(pick2.equals("Papers") && pick1.equals("Scissors")) return 1;*/
        String winner = "";
        for(int i=0;i<rules.size();i++){
            if(!rules.get(i).getWinner(player,computer).equals("")){
                winner = rules.get(i).getWinner(player,computer);
            }
        }

        if(winner.equals(player))return 1;
        else if(winner.equals(computer))return 2;
        else return -1;
    }



    private String toItem(int pick){
        if(pick == 1){
            return "Rock";
        }
        else if(pick == 2){
            return "Papers";
        }
        else if(pick == 3){
            return "Scissors";
        }
        else return "Error";
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
        if(started && !(input.equals("1") || input.equals("2") || input.equals("3") || input.equals(""))){
            System.out.println("Wrong input...try again.");
            readInput();
        }
        return input;
    }
}
