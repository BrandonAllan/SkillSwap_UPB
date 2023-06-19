package com.example.skillswap_upb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillswap_upb.databinding.ActivityApredizajeBinding

class ApredizajeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApredizajeBinding
    private lateinit var adaptador: AdaptadorUsuario

    var listaUsuarios = arrayListOf<Usuario>()

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
        listaUsuarios.add(Usuario("Pedro Pascal", "Busca aprender: Matematicas", "Enseña: Economia"))
        listaUsuarios.add(Usuario("Arthur Morgan", "Busca aprender: Algebra", "Enseña: Economia"))
        listaUsuarios.add(Usuario("Cloud Strife", "Busca aprender: Frances", "Enseña: Matematicas"))
        listaUsuarios.add(Usuario("Camila Aspizau", "Busca aprender: Calculo I", "Enseña: Ingles"))
        listaUsuarios.add(Usuario("Peter Cullen", "Busca aprender: Administracion", "Enseña: Emprendedurismo"))
        listaUsuarios.add(Usuario("James Arthur", "Busca aprender: Algebra Lineal", "Enseña: Programacion"))
        listaUsuarios.add(Usuario("Simo Hayha", "Busca aprender: Procesos", "Enseña: Economia"))
        listaUsuarios.add(Usuario("Deacon John", "Busca aprender: Programacion", "Enseña: Calculo II"))
        listaUsuarios.add(Usuario("Adrian Barba", "Busca aprender: Programacion", "Enseña: Ingles"))
        listaUsuarios.add(Usuario("Jose Kenny", "Busca aprender: Matematicas", "Enseña: Diseño"))
        listaUsuarios.add(Usuario("Ai Hoshino", "Busca aprender: Contabilidad Basica", "Enseña: Derecho"))
        listaUsuarios.add(Usuario("Jack Dias", "Busca aprender: Econometria", "Enseña: Cuentas Nacionales"))

    }

    fun setupRecyclerView() {
        binding.rvlista.layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorUsuario(listaUsuarios)
        binding.rvlista.adapter = adaptador

    }

    fun filtrar(texto: String) {
        var listaFiltrada = arrayListOf<Usuario>()

        listaUsuarios.forEach {
            if (it.ensenar.toLowerCase().contains(texto.toLowerCase())) {
                listaFiltrada.add(it)
            }
            adaptador.filtrar(listaFiltrada)
        }
    }
}