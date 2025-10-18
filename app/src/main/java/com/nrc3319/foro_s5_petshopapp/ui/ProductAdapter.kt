// ruta: app/src/main/java/com/nrc3319/foro_s5_petshopapp/ui/ProductAdapter.kt
package com.nrc3319.foro_s5_petshopapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load // La importación mágica para cargar imágenes fácilmente
import com.nrc3319.foro_s5_petshopapp.R
import com.nrc3319.foro_s5_petshopapp.data.Product

// El adaptador necesita recibir la lista de productos que va a mostrar.
class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    /**
     * Esta clase interna representa una sola "fila" o "tarjeta" en nuestra lista.
     * Guarda las referencias a los TextViews y al ImageView para no tener que buscarlos cada vez.
     */
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Buscamos cada elemento del layout por su ID, igual que en una Activity.
        private val nameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.productPriceTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.productImageView)

        // Este método conecta los datos de un único producto con los elementos visuales.
        fun bind(product: Product) {
            nameTextView.text = product.name
            priceTextView.text = String.format("$%.2f", product.price)

            // ¡Aquí ocurre la magia de Coil!
            // Le decimos al ImageView que cargue la imagen desde la URL del producto.
            imageView.load(product.imageUrl) {
                crossfade(true) // Una bonita animación de fundido
                placeholder(R.drawable.ic_launcher_background) // Qué mostrar mientras carga
                error(R.drawable.ic_launcher_foreground) // Qué mostrar si hay un error
            }
        }
    }

    /**
     * Se llama cuando RecyclerView necesita crear una nueva "tarjeta".
     * Infla (convierte de XML a objeto) nuestro layout 'item_product.xml'.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    /**
     * Se llama cuando RecyclerView quiere mostrar los datos de un producto en una "tarjeta" específica.
     * Usa el método bind que creamos en el ViewHolder.
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    /**
     * Le dice a RecyclerView cuántos elementos hay en total en la lista.
     */
    override fun getItemCount(): Int {
        return products.size
    }
}
