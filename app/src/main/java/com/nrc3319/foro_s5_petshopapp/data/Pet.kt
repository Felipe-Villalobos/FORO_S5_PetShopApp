package com.nrc3319.foro_s5_petshopapp.data

data class Pet(
    val id: String,
    val name: String,
    val breed: String, // Raza de la mascota
    val thumbnailUrl: String, // URL de una imagen en miniatura para la lista
    val videoUrl: String // URL del video que se reproducirá
)
