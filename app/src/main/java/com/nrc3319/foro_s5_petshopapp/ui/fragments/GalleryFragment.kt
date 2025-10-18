// ruta: app/src/main/java/com/nrc3319/foro_s5_petshopapp/ui/fragments/GalleryFragment.kt
package com.nrc3319.foro_s5_petshopapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nrc3319.foro_s5_petshopapp.R
import com.nrc3319.foro_s5_petshopapp.data.Pet
import com.nrc3319.foro_s5_petshopapp.ui.PetAdapter
import com.nrc3319.foro_s5_petshopapp.ui.VideoPlayerActivity

class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPets(view)
    }

    private fun setupPets(view: View) {
        val petsRecyclerView: RecyclerView = view.findViewById(R.id.petsRecyclerView)
        petsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val petList = listOf(
            Pet(
                id = "pet01", name = "Buddy", breed = "Golden Retriever",
                thumbnailUrl = "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-1/gen-3/screens/dash-vod-single-segment/video-vp9-360.webm"
            ),
            Pet(
                id = "pet02", name = "Luna", breed = "Gato Siamés",
                thumbnailUrl = "https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
            )
        )

        val petAdapter = PetAdapter(petList) { pet ->
            // Desde un Fragment, se usa requireActivity() para obtener el contexto para el Intent.
            val intent = Intent(requireActivity(), VideoPlayerActivity::class.java)
            intent.putExtra("VIDEO_URL", pet.videoUrl)
            startActivity(intent)
        }

        petsRecyclerView.adapter = petAdapter
    }
}
