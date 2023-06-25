package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Asteroid(
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double,
  is_triggered: Boolean = false
) : SpaceObject("Asteroid", '.', initialPosition, initialVelocity, radius, mass) {
    
    var explosion_happened: Boolean = is_triggered    

    fun explode(){
      this.explosion_happened = true
    }

  }
