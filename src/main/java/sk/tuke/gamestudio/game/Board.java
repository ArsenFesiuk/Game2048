package sk.tuke.gamestudio.game;

public class Board{
    Game1024 game1024;

    public Board(Game1024 game1024)
    {
        this.game1024 = game1024;
    }
    public void drawBoard() {
        System.out.println("\033[38;5;202mScore:\033[0m " + game1024.score);
        for (int i = 0; i < 4; i++) {
            System.out.println("\033[38;5;33m+------+------+------+------+\033[0m");
            for (int j = 0; j < 4; j++) {
                if (game1024.board[i][j] == 0) {
                    System.out.print("\033[38;5;33m|\033[0m      ");
                } else {
                    if(game1024.board[i][j] == 2) {
                        System.out.printf("\033[38;5;33m|\033[0m \033[1;33m%4d\033[0m ", game1024.board[i][j]);
                    }else if(game1024.board[i][j] == 4){
                        System.out.printf("\033[38;5;33m|\033[0m \033[38;5;202m%4d\033[0m ", game1024.board[i][j]);
                    }else if(game1024.board[i][j] == 8){
                        System.out.printf("\033[38;5;33m|\033[0m \033[0;38;5;129m%4d\033[0m ", game1024.board[i][j]);
                    }else if(game1024.board[i][j] == 16){
                        System.out.printf("\033[38;5;33m|\033[0m \033[38;5;46m%4d\033[0m ", game1024.board[i][j]);
                    }else if(game1024.board[i][j] == 32){
                        System.out.printf("\033[38;5;33m|\033[0m \033[38;5;219m%4d\033[0m ", game1024.board[i][j]);
                    }else if(game1024.board[i][j] == 64){
                        System.out.printf("\033[38;5;33m|\033[0m \033[0;33m%4d\033[0m ", game1024.board[i][j]);
                    }else if(game1024.board[i][j] == 128){
                        System.out.printf("\033[38;5;33m|\033[0m \u001B[38;2;135;38;87m%4d\033[0m ", game1024.board[i][j]);
                    }else if(game1024.board[i][j] == 256){
                        System.out.printf("\033[38;5;33m|\033[0m \033[38;5;45m%4d\033[0m ", game1024.board[i][j]);
                    }else if(game1024.board[i][j] == 512){
                        System.out.printf("\033[38;5;33m|\033[0m \033[95m%4d\033[0m ", game1024.board[i][j]);
                    }else if(game1024.board[i][j] == 1024){
                        System.out.printf("\033[38;5;33m|\033[0m \033[0m%4d\033[0m ", game1024.board[i][j]);
                    }
                }
            }
            System.out.println("\033[38;5;33m|\033[0m");
        }
        System.out.println("\033[38;5;33m+------+------+------+------+\033[0m");
    }
}
