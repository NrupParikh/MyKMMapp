package com.nrup.mykmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform