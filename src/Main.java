import boards.TicTacToeBoard;
import engineApi.GameEngine;
import gamePlay.*;
import engineApi.RuleEngine;
import engineApi.TicTacToeRuleEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine= new GameEngine();
        Board gameBoard= new TicTacToeBoard();
//        gameBoard.start();
        Player player1= new Player('X');
        Player player2 = new Player('0');
        GameConfig gameConfig= new GameConfig();
        boolean timed= true;
        if(timed) {
            gameConfig.setTimed(true);
            Time time= new Time(5000);
            gameConfig.setTimeControl(time);
            player1.setTimeLeft(new Time(time.getMilliseconds()));
            player2.setTimeLeft(new Time(time.getMilliseconds()));
        }
        GamePlay gamePlay= new GamePlay(gameBoard, gameConfig);
        startTicTacToePlay(gamePlay, new ArrayList<>(Arrays.asList(player1, player2)));
    }
    public static void startTicTacToePlay(GamePlay gamePlay, List<Player> players) {
        Player player1= players.get(0);
        Player player2= players.get(1);
        GameResult gameResult= new GameResult();
        RuleEngine ruleEngine= new TicTacToeRuleEngine();
        Scanner sc= new Scanner(System.in);
        Function<Player, Player> f= x-> x==player1?player2:player1;
        Player player= player2;
        while(!(gameResult.getGameOver())) {
            player= f.apply(player);
            long start = System.currentTimeMillis();
            System.out.println("Player"+player.getSymbol()+", make your move: ");
            System.out.println("Row: ");
            int row= sc.nextInt();
            System.out.println("Col: ");
            int col= sc.nextInt();
            long end = System.currentTimeMillis();
            Move move= new Move(row, col);
            gamePlay.makeMove(player, move);
            if(gamePlay.getGameConfig().isTimed()) {

                player.getTimeLeft().setMilliseconds(player.getTimeLeft().getMilliseconds()-(end-start));
                System.out.println(player.getTimeLeft().getMilliseconds());
            }
            gameResult= ruleEngine.updateGameResult(gamePlay, player);
            System.out.println(gamePlay.getBoard().toString());
        }
        if(gameResult.getDraw()) {
            System.out.println("DRAW");
        }
        else if(gameResult.getWinBy().equals("timeout")) {
            gameResult.setVictorious(f.apply(player));
            System.out.println(gameResult.getVictorious().getSymbol()+ " WINS due to timeout");
        }
        else {
            System.out.println(gameResult.getVictorious().getSymbol()+ " WINS");
        }


    }
}