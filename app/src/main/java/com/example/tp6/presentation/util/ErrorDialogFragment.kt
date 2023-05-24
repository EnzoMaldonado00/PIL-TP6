package com.example.tp6.presentation.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tp6.databinding.ErrorDialogBinding

class ErrorDialogFragment : DialogFragment() {

    private lateinit var binding: ErrorDialogBinding

    companion object {
        private const val ARGS_MESSAGE_KEY = "message"

        fun newInstance(message: String): ErrorDialogFragment {
            val fragment = ErrorDialogFragment()
            val args = Bundle()
            args.putString(ARGS_MESSAGE_KEY, message)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ErrorDialogBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val errorMessage = arguments?.getString(ARGS_MESSAGE_KEY)
        binding.errorMessage.text = errorMessage
        binding.confirmButton.setOnClickListener {
            dismiss()
        }
    }
}
