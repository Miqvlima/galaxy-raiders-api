package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import java.time.LocalDateTime

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File

open class GameData(
    dateTime: String,
    initialScore: Double
) {
    
    var score: Double = initialScore

    var numberAsteroidsDestroyed: Int = 0

    fun updateScore(points: Double){
        this.score += points
    }

    fun updateNumberAsteroidsDestroyed(){
        this.numberAsteroidsDestroyed += 1
    }

    fun addGameEntryToJson(){
        val mapper = jacksonObjectMapper()
        val jsonString = File("../score/Scoreboard.json").readText(Charsets.UTF_8)
        val gameEntriesFromJson: MutableList<GameData> = mapper.readValue(jsonString)
        gameEntriesFromJson.add(this) 
        val gameEntriesToJson = mapper.writeValueAsString(gameEntriesFromJson)
        File("../score/Scoreboard.json").writeText(gameEntriesToJson)        
    }

    fun updateJsonWithScore(){
        val mapper = jacksonObjectMapper()
        val jsonString = File("../score/Scoreboard.json").readText(Charsets.UTF_8)
        var gameEntriesFromJson: MutableList<GameData> = mapper.readValue(jsonString)
        val gameEntriesLastIndex = gameEntriesFromJson.size - 1

        gameEntriesFromJson[gameEntriesLastIndex].score = this.score
        gameEntriesFromJson[gameEntriesLastIndex].numberAsteroidsDestroyed = this.numberAsteroidsDestroyed

        val gameEntriesToJson = mapper.writeValueAsString(gameEntriesFromJson)
        File("../score/Scoreboard.json").writeText(gameEntriesToJson)   
    }    

    fun updateLeaderboard(){
        val mapper = jacksonObjectMapper()
        val jsonString = File("../score/Scoreboard.json").readText(Charsets.UTF_8)
        var gameEntriesFromJson: MutableList<GameData> = mapper.readValue(jsonString)
        val gameEntriesSorted: MutableList<GameData> = gameEntriesFromJson.sortedByDescending { it.score }.toMutableList()
        var topThreeGameEntries: MutableList<GameData> = arrayListOf()
        
        for (i in 1..3){
            try {
                topThreeGameEntries.add(gameEntriesSorted[i])
            }
            catch (e: IndexOutOfBoundsException){
                break
            }
        }

        val topThreeGameEntriesToJson = mapper.writeValueAsString(topThreeGameEntries)
        File("../score/Leaderboard.json").writeText(topThreeGameEntriesToJson)
    }
}