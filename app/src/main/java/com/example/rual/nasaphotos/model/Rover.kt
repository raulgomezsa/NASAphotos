package com.example.rual.nasaphotos.model

import java.text.SimpleDateFormat
import java.util.*

class Rover (
            val id: Int,
            val name: String,
            val landing_date: Date,
            val launch_date: Date,
            val status: String,
            val max_sol: Int,
            val max_date: Date,
            val total_photos: Int,
            val cameras: List<Camera>
) {
    override fun toString(): String {
        return "\n\t[id=" + id +
                ",\n\tname=" + name +
                ",\n\tlanding_date=" + SimpleDateFormat("yyyy-MM-dd").format(landing_date).toString() +
                ",\n\tlaunch_date=" + SimpleDateFormat("yyyy-MM-dd").format(launch_date).toString() +
                ",\n\tstatus=" + status +
                ",\n\tmax_sol=" + max_sol +
                ",\n\tmax_date=" + SimpleDateFormat("yyyy-MM-dd").format(max_date) +
                ",\n\ttotal_photos=" + total_photos +
                ",\n\tcameras:\t" + cameras.toString()
                "]"
    }
}