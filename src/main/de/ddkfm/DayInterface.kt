package de.ddkfm

interface DayInterface<T, U> {
    fun getInput() : T
    fun check(inputFile : T) : U
    fun check2(inputFile : T) : U
}