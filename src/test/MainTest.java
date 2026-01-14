package test;
import boards.TicTacToeBoard;
import engineApi.GameEngine;
import engineApi.RuleEngine;
import engineApi.TicTacToeRuleEngine;
import gamePlay.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Before
    public void setup() {

    }

    public GameResult startTicTacToePlay(int[][] player1move, int[][] player2move) {
        GameEngine gameEngine= new GameEngine();
        Board board= new TicTacToeBoard();
        Player player1= new Player('X');
        Player player2 = new Player('0');
        GameResult gameResult= new GameResult();
        GamePlay gamePlay = new GamePlay();
        RuleEngine ruleEngine= new TicTacToeRuleEngine();
        Scanner sc= new Scanner(System.in);
        Function<Player, Player> f= x-> x==player1?player2:player1;
        Function<int[][], int[][]> f2= x-> x==player1move?player2move:player1move;
        Player player= player2;
        int[][] playerMove= player2move;
        int moveNo=0;
        while(!(gameResult.getGameOver())) {
            player= f.apply(player);
            playerMove= f2.apply(playerMove);
            Move move= new Move(playerMove[moveNo/2][0], playerMove[moveNo/2][1]);
            gamePlay.makeMove(board, player, move);
            gameResult= ruleEngine.updateGameResult(board, player);
            moveNo++;
        }
        System.out.println(board.toString());
        return gameResult;

    }
    @Test
    public void checkForDiagWin() {
        int[][] player1move= new int[][]{{0, 0}, {1, 1}, {2, 2}};
        int[][] player2move= new int[][]{{1, 0}, {2, 1}, {1, 2}};
        GameResult gameResult= startTicTacToePlay(player1move, player2move);
        assertEquals('X', gameResult.getVictorious().getSymbol());

    }
    @Test
    public void checkForRowWin() {
        int[][] player1move= new int[][]{{1, 0}, {1, 1}, {1, 2}};
        int[][] player2move= new int[][]{{0, 0}, {2, 1}, {0, 2}};
        GameResult gameResult= startTicTacToePlay(player1move, player2move);
        assertEquals('X', gameResult.getVictorious().getSymbol());

    }
    @Test
    public void checkForColWin() {
        int[][] player1move= new int[][]{{0, 2}, {1, 2}, {2, 2}};
        int[][] player2move= new int[][]{{1, 0}, {2, 1}, {1, 1}};
        GameResult gameResult= startTicTacToePlay(player1move, player2move);
        assertEquals('X', gameResult.getVictorious().getSymbol());

    }

    @Test
    public void checkForDiag2Win() {
        int[][] player1move= new int[][]{{0, 1}, {2, 2}, {1, 0}};
        int[][] player2move= new int[][]{{0, 2}, {1, 1}, {2, 0}};
        GameResult gameResult= startTicTacToePlay(player1move, player2move);
        assertEquals('0', gameResult.getVictorious().getSymbol());

    }
    @Test
    public void checkForDraw() {
        int[][] player1move= new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 1}, {1, 2}};
        int[][] player2move= new int[][]{{0, 1}, {1, 0}, {2, 2}, {2, 0}};
        GameResult gameResult= startTicTacToePlay(player1move, player2move);
        assertEquals(true, gameResult.getDraw());

    }


}
