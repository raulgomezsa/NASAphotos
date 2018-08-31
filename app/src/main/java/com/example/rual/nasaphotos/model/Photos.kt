package com.example.rual.nasaphotos.model

class Photos (
        val photos: List<Photo>) {

    override fun toString(): String {
        return "[photos=\n" + photos +
                "\n]"
    }
}