package com.nrc3319.foro_s5_petshopapp.ui

// ui/ProductAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup    import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.layout.layout
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.compose.material.placeholder
import coil.load
import com.example.petstoreapp.R
import com.example.petstoreapp.data.Product

class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.productPriceTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.productImageView)

        fun bind(product: Product) {
            nameTextView.text = product.name
            priceTextView.text = String.format("$%.2f", product.price)
            // Cargar imagen con Coil
            imageView.load(product.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background) // Imagen de placeholder
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size
}
