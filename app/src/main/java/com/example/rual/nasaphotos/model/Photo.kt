package com.example.rual.nasaphotos.model

import java.text.SimpleDateFormat
import java.util.*

class Photo (
    val id: Int,
    val sol: Int,
    val camera: Camera,
    val img_src: String,
    val earth_date: Date,
    val rover: Rover
) {
    override fun toString(): String {
        return "[id=" + id +
                ",\nsol=" + sol +
                ",\ncamera=" + camera +
                ",\nimg_src" + img_src +
                ",\nearth_date" + SimpleDateFormat("yyyy-MM-dd").format(earth_date) +
                ",\nrover:\t" + rover +
                "]"
    }
}