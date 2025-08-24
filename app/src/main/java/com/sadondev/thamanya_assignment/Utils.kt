package com.sadondev.thamanya_assignment

fun formatTime(seconds: Long): String {
    val hours = seconds / 3600L
    val minutes = (seconds % 3600L) / 60L
    val secs = seconds % 60L

    val parts = mutableListOf<String>()
    if (hours > 0L) parts.add("${hours}H")
    if (minutes > 0L) parts.add("${minutes}m")
    if (secs > 0L && hours == 0L) parts.add("${secs}s") // show seconds only if < 1 hour

    return parts.joinToString(" ")
}
