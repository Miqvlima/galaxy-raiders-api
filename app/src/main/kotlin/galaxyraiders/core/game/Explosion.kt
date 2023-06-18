package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double,
) : SpaceObject("Explosion", 'E', initialPosition, initialVelocity, radius, mass) {
    var is_triggered: Boolean = false
    var lifetime = 25

    fun tick(){
        this.tick--
        if(this.tick == 0){
            this.is_triggered = true
        }
    }

}