package com.bilgiland.todo.utility

/**
 * @return random string by length
 */
fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}