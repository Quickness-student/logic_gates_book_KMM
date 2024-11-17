package org.logic_gates_book

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform