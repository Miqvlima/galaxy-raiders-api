@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() {        
     return Math.sqrt((this.dx*this.dx)+(this.dy*this.dy))
    }

  val radiant: Double
    get(){
    
      var vector_magnitude: Double = this.magnitude
      var cosine: Double = dx/vector_magnitude
      var angle: Double = Math.acos(cosine)
    
      if (radiantQuadrant(angle) != vectorQuadrant(dx, dy)){
        angle = -angle
      }
    
      return angle      
    } 

  val degree: Double    
    get(){
    
      val radians: Double = this.radiant
    
      return (radians*180)/Math.PI
    }

  val unit: Vector2D
    get(){
    
      val new_dx: Double = dx/this.magnitude
      val new_dy: Double = dy/this.magnitude
    
      return Vector2D(new_dx, new_dy)
    }

  val normal: Vector2D
    get() {
      return normalVector(this)
    }

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(dx*scalar, dy*scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(dx/scalar, dy/scalar)
  }

  operator fun times(v: Vector2D): Double {
    return (dx*v.dx + dy*v.dy)
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(dx + v.dx, dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(this.dx + p.x, this.dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(-dx, -dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(dx - v.dx, dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    
    val magnitude: Double = target.magnitude
    val scalar_product: Double = dx*target.dx + dy*target.dy
    
    return scalar_product/magnitude 
  }

  fun vectorProject(target: Vector2D): Vector2D {
    
    val scalar_projection: Double = this.scalarProject(target)
    
    return scalar_projection * target.unit
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(this*v.dx, this*v.dy)
}

fun vectorQuadrant(x: Double, y: Double): Int {
  if (x == 0.0){
    if (y > 0)
      return 2
    else if (y < 0) 
      return 4
    else
      return 1
  }
  else if (y == 0.0){
    if (x > 0) 
      return 1
    else if (x < 0) 
      return 3
  }
  else if ((x>0) && (y>0)){
    return 1
  }
  else if ((x<0) && (y>0)){
    return 2
  }
  else if((x<0) && (y<0)){
    return 3
  }
  else if ((x>0) && (y<0)){
    return 4
  }
  return 0
}

fun radiantQuadrant(angle: Double): Int {
  if ((angle>=0) && (angle<(Math.PI/2))) return 1
  else if ((angle>=(Math.PI/2)) && (angle<Math.PI)) return 2
  else if ((angle<0) && (angle>=-(Math.PI/2))) return 4
  else if ((angle>=-(Math.PI)) && (angle<-(Math.PI/2))) return 3
  return 0
}

fun normalVector(vector: Vector2D): Vector2D {
  
  val vector_quadrant: Int = vectorQuadrant(vector.dx, vector.dy)
  val normal_without_unit: Vector2D = Vector2D(1.0, -vector.dx/vector.dy)
  var normal_vector: Vector2D = Vector2D(normal_without_unit.unit.dx, normal_without_unit.unit.dy)
  val normal_quadrant: Int = vectorQuadrant(normal_vector.dx, normal_vector.dy)
  
  if ((vector_quadrant == 1) && (normal_quadrant == 4)){
    return normal_vector
  }
  
  else if (normal_quadrant == vector_quadrant - 1){
    return normal_vector
  }
  
  return -normal_vector

}
