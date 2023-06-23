package com.example.skillswap_upb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillswap_upb.adapter.AdaptadorUsuarioEnseñar
import com.example.skillswap_upb.databinding.ActivityApredizajeBinding

class ApredizajeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApredizajeBinding
    private lateinit var adaptador: AdaptadorUsuarioEnseñar

    var listaUsuarios = arrayListOf<UsuarioEnseñar>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApredizajeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        llenarLista()
        setupRecyclerView()
        binding.etBuscador.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                filtrar(p0.toString())
            }

        })
    }

    fun llenarLista() {
        listaUsuarios.add(UsuarioEnseñar("Pedro Pascal", "Busca aprender: Matematicas", "Enseña: Economia"))
        listaUsuarios.add(UsuarioEnseñar("Arthur Morgan", "Busca aprender: Algebra", "Enseña: Economia"))
        listaUsuarios.add(UsuarioEnseñar("Cloud Strife", "Busca aprender: Frances", "Enseña: Matematicas"))
        listaUsuarios.add(UsuarioEnseñar("Camila Aspizau", "Busca aprender: Calculo I", "Enseña: Ingles"))
        listaUsuarios.add(UsuarioEnseñar("Peter Cullen", "Busca aprender: Administracion", "Enseña: Emprendedurismo"))
        listaUsuarios.add(UsuarioEnseñar("James Arthur", "Busca aprender: Algebra Lineal", "Enseña: Programacion"))
        listaUsuarios.add(UsuarioEnseñar("Simo Hayha", "Busca aprender: Procesos", "Enseña: Economia"))
        listaUsuarios.add(UsuarioEnseñar("Deacon John", "Busca aprender: Programacion", "Enseña: Calculo II"))
        listaUsuarios.add(UsuarioEnseñar("Adrian Barba", "Busca aprender: Programacion", "Enseña: Ingles"))
        listaUsuarios.add(UsuarioEnseñar("Jose Kenny", "Busca aprender: Matematicas", "Enseña: Diseño"))
        listaUsuarios.add(UsuarioEnseñar("Ai Hoshino", "Busca aprender: Contabilidad Basica", "Enseña: Derecho"))
        listaUsuarios.add(UsuarioEnseñar("Jack Dias", "Busca aprender: Econometria", "Enseña: Cuentas Nacionales"))

    }

    fun setupRecyclerView() {
        binding.rvlista.layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorUsuarioEnseñar(listaUsuarios)
        binding.rvlista.adapter = adaptador

    }

    fun filtrar(texto: String) {
        var listaFiltrada = arrayListOf<UsuarioEnseñar>()

        listaUsuarios.forEach {
            if (it.ensenar.toLowerCase().contains(texto.toLowerCase())) {
                listaFiltrada.add(it)
            }
            adaptador.filtrar(listaFiltrada)
        }
    }
}