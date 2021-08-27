package dev.kibet.movetechtest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.kibet.movetechtest.R
import dev.kibet.movetechtest.databinding.FragmentSendMessageBinding
import dev.kibet.movetechtest.others.Status
import dev.kibet.movetechtest.ui.viewmodel.MainViewModel

@AndroidEntryPoint
class SendMessageFragment : Fragment() {
    private lateinit var binding: FragmentSendMessageBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendMessageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObersevers()

        val progressBar = binding.progressBar

        binding.buttonSend.setOnClickListener {
            binding.apply {
                val username = username.text.trim().toString()
                val apikey = apikey.text.trim().toString()
                val sender = sender.text.trim().toString()
                val sendTo = sendTo.text.trim().toString()
                val message = yourMessage.text.trim().toString()
                val msgtype = msgtype.text.trim().toString()
                val delivery = deliverry.text.trim().toString()

                progressBar.visibility = View.VISIBLE

                viewModel.sendSms(
                    username = username,
                    api_key = apikey,
                    sender = sender,
                    sendTo = sendTo,
                    message = message,
                    msgtype = msgtype,
                    delivery = delivery
                )
            }
        }
    }

    private fun subscribeToObersevers() {
        val progressBar = binding.progressBar
        viewModel.sendSmsStatus.observe(viewLifecycleOwner, { result ->
            result.let {
                when (result.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        findNavController().navigate(R.id.action_sendMessageFragment_to_successFragment)
                    }

                    Status.LOADING -> {

                    }

                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, "${result.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}