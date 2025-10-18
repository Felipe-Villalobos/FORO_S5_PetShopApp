// ruta: app/src/main/java/com/nrc3319/foro_s5_petshopapp/ui/fragments/StoreFragment.kt
package com.nrc3319.foro_s5_petshopapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nrc3319.foro_s5_petshopapp.R
import com.nrc3319.foro_s5_petshopapp.data.Product
import com.nrc3319.foro_s5_petshopapp.ui.ProductAdapter

class StoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProducts(view)
    }

    private fun setupProducts(view: View) {
        val productsRecyclerView: RecyclerView = view.findViewById(R.id.productsRecyclerView)
        // He cambiado el layout a vertical para que se vea mejor en su propia pestaña
        productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productList = listOf(
            Product(
                id = "p001", name = "Juguete Hueso de Goma", description = "", price = 12.99,
                imageUrl = "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Product(
                id = "p002", name = "Alimento Seco Gatos", description = "", price = 45.50,
                imageUrl = "https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Product(
                id = "p003", name = "Jaula para Hámster", description = "", price = 35.00,
                imageUrl = "https://www.agrocampo.com.co/media/catalog/product/cache/d51e0dc10c379a6229d70d752fc46d83/_/3/_3_0_303040080-min.jpg"
            )
        )
        productsRecyclerView.adapter = ProductAdapter(productList)
    }
}
