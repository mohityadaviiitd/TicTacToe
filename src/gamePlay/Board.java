package gamePlay;

public interface Board {

    char getCellValue(Move move);

    void setCellValue(Player player, Move move);
}
