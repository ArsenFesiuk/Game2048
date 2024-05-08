package sk.tuke.gamestudio.game;

public class Move {
    Game1024 game1024;

    public Move(Game1024 game1024) {
        this.game1024 = game1024;
    }

    public boolean moveRight(){
        boolean moved = false;
        for(int i = 0; i < 4; i++){
            for (int j = 0;j < 4;j++){
                for(int s = 4-1;s > j;s--){
                    if(game1024.board[i][s] == 0){
                        if(game1024.board[i][s-1] != 0){
                            game1024.board[i][s] = game1024.board[i][s-1];
                            game1024.board[i][s-1] = 0;
                            moved = true;
                        }
                    }
                    if((game1024.board[i][s] == game1024.board[i][s-1])){
                        game1024.board[i][s] = game1024.board[i][s] + game1024.board[i][s-1];
                        game1024.board[i][s-1] = 0;
                        game1024.score += game1024.board[i][s];
                        moved = true;
                    }
                }
            }
        }
        if(moved){
            game1024.addRandomTile();
        }
        return moved;
    }

    public boolean moveLeft(){
        boolean moved = false;
        for(int i = 0; i < 4; i++){
            for (int j = 0;j < 4;j++){
                for(int s = 0;s < 4-1;s++){
                    if(game1024.board[i][s] == 0){
                        if(game1024.board[i][s+1] != 0){
                            game1024.board[i][s] = game1024.board[i][s+1];
                            game1024.board[i][s+1] = 0;
                            moved = true;
                        }
                    }
                    if((game1024.board[i][s] == game1024.board[i][s+1])){
                        game1024.board[i][s] = game1024.board[i][s] + game1024.board[i][s+1];
                        game1024.board[i][s+1] = 0;
                        game1024.score += game1024.board[i][s];
                        moved = true;
                    }

                }
            }
        }
        if(moved){
            game1024.addRandomTile();
        }
        return moved;
    }

    public boolean moveUp() {
        boolean moved = false;
        for(int i = 0; i < 4; i++){
            for (int j = 0;j < 4;j++){
                for(int s = 0;s < 4-1;s++){
                    if(game1024.board[s][j] == 0){
                        if(game1024.board[s+1][j] != 0){
                            game1024.board[s][j] = game1024.board[s+1][j];
                            game1024.board[s+1][j] = 0;
                            moved = true;
                        }
                    }
                    if((game1024.board[s][j] == game1024.board[s+1][j])){
                        game1024.board[s][j] = game1024.board[s][j] + game1024.board[s+1][j];
                        game1024.board[s+1][j] = 0;
                        game1024.score += game1024.board[s][j];
                        moved = true;
                    }

                }
            }
        }
        if(moved){
            game1024.addRandomTile();
        }
        return moved;
    }

    public boolean moveDown() {
        boolean moved = false;
        for(int i = 0; i < 4; i++){
            for (int j = 0;j < 4;j++){
                for(int s = 4-1;s > i;s--){
                    if(game1024.board[s][j] == 0){
                        if(game1024.board[s-1][j] != 0){
                            game1024.board[s][j] = game1024.board[s-1][j];
                            game1024.board[s-1][j] = 0;
                            moved = true;
                        }
                    }
                    if((game1024.board[s][j] == game1024.board[s-1][j])){
                        game1024.board[s][j] = game1024.board[s][j] + game1024.board[s-1][j];
                        game1024.board[s-1][j] = 0;
                        game1024.score += game1024.board[s][j];
                        moved = true;
                    }

                }
            }
        }
        if(moved){
            game1024.addRandomTile();
        }
        return moved;
    }
}
