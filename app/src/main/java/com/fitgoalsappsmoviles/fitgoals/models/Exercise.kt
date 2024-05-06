package com.fitgoalsappsmoviles.fitgoals.models

//Modelo de los campos pertenecientes a un documento dentro de la colección "Exercise"

data class Exercise(
    val name: String = "", //Nombre del Ejercicio
    val imageUrl: String = "", //Url de la imagen del ejercicio
    val muscleGroupId: String = "", //Id del Grupo Muscular en específico
    val series: String = "", //Series que posee el ejercicio
    val videoUrl: String = "", //Url del video en Youtube del tutorial del ejercicio
    val description: String = "", //Descripción de cómo se hace el ejercicio
)