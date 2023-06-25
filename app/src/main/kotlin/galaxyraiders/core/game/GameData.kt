package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import java.time.LocalDateTime

open class GameData(
    dateTime: String,
    initialScore: Double
) {
    
    var score: Double = initialScore

    fun updateScore(points: Double){
        this.score += points
    }

    fun addGameEntryToJson(){
        return
    }

    fun updateJsonWithScore(){
        return
    }    

}