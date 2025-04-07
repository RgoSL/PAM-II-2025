package com.data

val userDefault = User(
    userId = "Aluno",
    name = "Aluno DS",
    avatarUrl = null,
    age = 16,
    score = 75,
    registerDate = "07/04/2025",
    email = "example@etec.sp.gov.br",
    bio = "Estou Aprendendo a Passar Paramêtros no JETPACK COMPOSE"
)

val professor = User (
    userId = "Marcelo",
    name = "Marcelo",
    avatarUrl = null,
    age = 51,
    score = 90,
    registerDate = "15/06/1973",
    email = "marcelo.collado@etec.sp.gov.br",
    bio = "Passei os Paramêtros de Usuários"
)

fun getUserData(userName: String): User{
    return if (userName == "Marcelo") {
        professor
    } else  {
        userDefault
    }
}