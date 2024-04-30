package org.game

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class GameTest{
   @Test
    fun testGame(){
         val game = Game(3)
         assertNotNull(game)
    }
    @Test
    fun testBoard(){
        val game = Game(3)
        assertEquals(3, game.Board.size)
        assertEquals(3, game.Board[0].size)
    }
    @Test
    fun testPutX(){
        val game = Game(3)
        game.putX(0,0)
        assertEquals(1, game.Board[0][0])
    }
    @Test
    fun testPutO(){
        val game = Game(3)
        game.putO(0,0)
        assertEquals(2, game.Board[0][0])
    }
    @Test
    fun testRestart(){
        val game = Game(3)
        game.putO(0,0)
        game.putX(1,1)
        game.putO(2,2)
        game.putX(0,1)
        game.putO(1,0)
        game.putX(2,0)
        game.putO(0,2)
        game.putX(1,2)
        game.putO(2,1)
        game.putX(2,2)
        game.putO(1,1)
        game.putX(0,0)
        game.restart();
    }
    @Test
    fun testCheckWin(){
        val game = Game(3)
        game.putX(1,0)
        game.putX(1,1)
        game.putX(1,2)
        assertEquals(1, game.checkWin())
    }
}
