@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

data class Point2D(val x: Double, val y: Double) {
  operator fun plus(p: Point2D): Point2D {
    return Point2D(x + p.x, y + p.y)
  }

  operator fun plus(v: Vector2D): Point2D {
    return Point2D(x + v.dx, y + v.dy)
  }

  override fun toString(): String {
    return "Point2D(x=$x, y=$y)"
  }

  fun toVector(): Vector2D {
    return Vector2D(x, y)
  }

  fun impactVector(p: Point2D): Vector2D {
    return Vector2D(Math.abs(x - p.x), Math.abs(y - p.y))
  }

  fun impactDirection(p: Point2D): Vector2D {
    
    val x_value: Double = Math.abs(x - p.x)
    val y_value: Double = Math.abs(y - p.y)
    val impact_vector: Vector2D = Vector2D(x_value, y_value)
    
    return Vector2D(impact_vector.unit.dx, impact_vector.unit.dy)
  }

  fun contactVector(p: Point2D): Vector2D {
    
    val impact_vector: Vector2D = this.impactVector(p)
    
    return Vector2D(impact_vector.normal.dx, impact_vector.normal.dy)
  }

  fun contactDirection(p: Point2D): Vector2D {
    
    val contact_direction: Vector2D = this.impactVector(p)
    
    return Vector2D(contact_direction.normal.dx, contact_direction.normal.dy)
  }

  fun distance(p: Point2D): Double {
    
    val horizontal_distance: Double = Math.abs(x - p.x)
    val vertical_distance: Double = Math.abs(y - p.y)
    
    return Math.sqrt((horizontal_distance * horizontal_distance) + (vertical_distance* vertical_distance))
  }
}
