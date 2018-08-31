package com.example.rual.nasaphotos.model

class Camera (
        val id: Int =0,
        val name: String,
        val rover_id: Int=0,
        val full_name: String
) {
    override fun toString(): String {
        return "\n\t[id=" + id +
                ",\n\tname=" + name +
                ",\n\trover_id=" + rover_id +
                ",\n\tfull_name=" + full_name +
                "]"
    }
}