import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        System.out.println("Welcome to Rock, Papers, Scissors!\n");
        System.out.println();
        showMenu();

    }

    public static void showMenu(){

        System.out.println("1. Play Game");
        System.out.println("2. Exit\n");
        System.out.println("Choose a number:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try{
           input = reader.readLine();

        }catch (IOException e){
            System.out.println("Wrong input...try again\n\n");
            showMenu();
        }
        if(input.equals("1")){
            startGame();
        }
        else if(input.equals("2")){
            System.exit(0);
        }
        else{
            System.out.println("Wrong input...try again\n\n");
            showMenu();
        }

    }

    public static void startGame(){
        Game game = new Game();
        game.startGame();
    }
}
