package com.lugares_v.ui.lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lugares_v.R
import com.lugares_v.databinding.FragmentAddLugarBinding
import com.lugares_v.databinding.FragmentUpdateLugarBinding
import com.lugares_v.model.Lugar
import com.lugares_v.viewmodel.LugarViewModel

class UpdateLugarFragment : Fragment() {


    //Se recupera un argumento
    private val args by navArgs<UpdateLugarFragmentArgs>()

    private var _binding: FragmentUpdateLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var lugarViewModel: LugarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lugarViewModel =  ViewModelProvider(this).get(LugarViewModel::class.java)
        _binding = FragmentUpdateLugarBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.lugar.nombre)
        binding.etCorreoLugar.setText(args.lugar.correo)
        binding.etTelefono.setText(args.lugar.telefono)
        binding.etWeb.setText(args.lugar.web)

        binding.btUpdateLugar.setOnClickListener { updateLugar() }

        return binding.root
    }

    private fun updateLugar() {
        val nombre = binding.etNombre.text.toString()
        val correo  = binding.etCorreoLugar.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web  = binding.etWeb.text.toString()

        if (nombre.isNotEmpty()){
            val lugar = Lugar(args.lugar.id,nombre,correo,web,telefono,
                args.lugar.latitud,
                args.lugar.longitud,
                args.lugar.altura,
                args.lugar.rutaAudio,
                args.lugar.rutaimagen)
            lugarViewModel.saveLugar(lugar)
            Toast.makeText(requireContext(),getString(R.string.msg_lugar_updated),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addLugarFragment_to_nav_lugar)
        }else {
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}