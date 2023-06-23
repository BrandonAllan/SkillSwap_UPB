package com.example.skillswap_upb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skillswap_upb.R
import com.example.skillswap_upb.UsuarioEnseñar


class AdaptadorUsuarioEnseñar(
    var listaUsuarios: ArrayList<UsuarioEnseñar>
): RecyclerView.Adapter<AdaptadorUsuarioEnseñar.ViewHolder>()     {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNombre = itemView.findViewById(R.id.tvNombre) as TextView
        val tvAprender = itemView.findViewById(R.id.tvAprender) as TextView
        val tvEnsenar = itemView.findViewById(R.id.tvEnsenar) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_usuario, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = listaUsuarios[position]
        holder.tvNombre.text = usuario.nombre
        holder.tvAprender.text = usuario.aprender
        holder.tvEnsenar.text = usuario.ensenar
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }
    fun filtrar(listaFiltrada: ArrayList<UsuarioEnseñar>) {
        this.listaUsuarios = listaFiltrada
        notifyDataSetChanged()

    }

}