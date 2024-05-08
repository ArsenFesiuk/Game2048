package sk.tuke.gamestudio.game;

public class WonOrOver {
    Game1024 game1024;

    public WonOrOver(Game1024 game1024) {
        this.game1024 = game1024;
    }

    public boolean isGameWon(){
        for(int i = 0; i < 4; i++){
            for(int j = 0;j < 4; j++){
                if (game1024.board[i][j] == 1024){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isGameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (game1024.board[i][j] == 0) {
                    return false;
                }
                if (i < 3 && game1024.board[i][j] == game1024.board[i + 1][j]) {
                    return false;
                }
                if (j < 3 && game1024.board[i][j] == game1024.board[i][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

}
