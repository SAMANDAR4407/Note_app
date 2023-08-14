package uz.gita.noteapppractika.utils

fun <T> T.myApply( block: T.()-> Unit) {
    block(this)
}