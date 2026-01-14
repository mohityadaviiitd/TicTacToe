import boards.TicTacToeBoard;
import engineApi.GameEngine;
import gamePlay.Board;
import gamePlay.GameResult;
import engineApi.RuleEngine;
import gamePlay.GamePlay;
import gamePlay.Move;
import gamePlay.Player;
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
        startTicTacToePlay(gameBoard, new ArrayList<>(Arrays.asList(player1, player2)));
    }
    public static void startTicTacToePlay(Board board, List<Player> players) {
        Player player1= players.get(0);
        Player player2= players.get(1);
        GameResult gameResult= new GameResult();
        GamePlay gamePlay = new GamePlay();
        RuleEngine ruleEngine= new TicTacToeRuleEngine();
        Scanner sc= new Scanner(System.in);
        Function<Player, Player> f= x-> x==player1?player2:player1;
        Player player= player2;
        while(!(gameResult.getGameOver())) {
            player= f.apply(player);
            System.out.println("Player"+player.getSymbol()+", make your move: ");
            System.out.println("Row: ");
            int row= sc.nextInt();
            System.out.println("Col: ");
            int col= sc.nextInt();
            Move move= new Move(row, col);
            gamePlay.makeMove(board, player, move);
            gameResult= ruleEngine.updateGameResult(board, player);
            System.out.println(board.toString());
        }
        if(gameResult.getDraw()) {
            System.out.println("DRAW");
        }
        else {
            System.out.println(gameResult.getVictorious().getSymbol()+ " WINS");
        }


    }
}