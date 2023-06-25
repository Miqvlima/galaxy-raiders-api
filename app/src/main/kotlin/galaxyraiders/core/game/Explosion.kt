package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(
    asteroid: Asteroid
) : SpaceObject("Explosion", 'E', asteroid.center, asteroid.velocity, asteroid.radius, asteroid.mass) {
    val score: Double = asteroid.radius * asteroid.mass
}