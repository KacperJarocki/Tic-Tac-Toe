package org.game

class Game(Size:Int) {
    val Board:Array<Array<Int>> = Array(Size) { Array(Size) { 0 } };
    var whoseTurn:Boolean = false;
    fun putX(i: Int, i1: Int) {
        Board[i][i1] = 1
        whoseTurn = false;
    }
    fun whoseTurnIsIt():Char{
        return if(whoseTurn) 'X' else 'O'
    }
    fun putO(i: Int, i1: Int) {
        Board[i][i1] = 2
        whoseTurn = true;
    }
    fun getIndex(i: Int, i1: Int):Char = if(Board[i][i1] == 1) 'X' else if(Board[i][i1] == 2) 'O' else ' '

    fun restart() {
      for(i in 0 until Board.size){
          for(j in 0 until Board[i].size){
              Board[i][j] = 0
          }
      }
    }
    fun checkWin():Int{
        for(i in 0 until Board.size){
            if(Board[i][0] == Board[i][1] && Board[i][1] == Board[i][2] && Board[i][0] != 0){
                return Board[i][0]
            }
            if(Board[0][i] == Board[1][i] && Board[1][i] == Board[2][i] && Board[0][i] != 0){
                return Board[0][i]
            }
        }
        if(Board[0][0] == Board[1][1] && Board[1][1] == Board[2][2] && Board[0][0] != 0){
            return Board[0][0]
        }
        if(Board[0][2] == Board[1][1] && Board[1][1] == Board[2][0] && Board[0][2] != 0){
            return Board[0][2]
        }
        return 0
    }

}