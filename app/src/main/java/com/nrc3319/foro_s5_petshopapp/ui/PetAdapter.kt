package com.nrc3319.foro_s5_petshopapp.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nrc3319.foro_s5_petshopapp.R
import com.nrc3319.foro_s5_petshopapp.data.Pet

// El adaptador ahora recibe dos cosas:
// 1. La lista de mascotas (pets)
// 2. Una función lambda (onPetClicked) que se ejecutará cuando se haga clic en una mascota.
class PetAdapter(
    private val pets: List<Pet>,
    private val onPetClicked: (Pet) -> Unit
) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.petNameTextView)
        private val breedTextView: TextView = itemView.findViewById(R.id.petBreedTextView)
        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.petThumbnailImageView)

        // El método bind ahora también necesita la función que se ejecutará al hacer clic.
        fun bind(pet: Pet, onPetClicked: (Pet) -> Unit) {
            nameTextView.text = pet.name
            breedTextView.text = pet.breed

            // Usamos Coil para cargar la imagen en miniatura.
            thumbnailImageView.load(pet.thumbnailUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_foreground)
            }

            // ¡IMPORTANTE! Aquí definimos qué pasa cuando el usuario toca la tarjeta completa.
            itemView.setOnClickListener {
                onPetClicked(pet) // Se ejecuta la función que nos pasaron, enviando la mascota seleccionada.
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pet, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        // Al enlazar, le pasamos la mascota y la función de clic.
        holder.bind(pets[position], onPetClicked)
    }

    override fun getItemCount() = pets.size
}
