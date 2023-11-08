package com.example.async

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.async.databinding.FragmentNumberBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NumberFragment : Fragment() {

    private var _binding: FragmentNumberBinding? = null
    private val binding get() = _binding!!

    private val adapter: NumberAdapter = NumberAdapter()
//    private val viewModel: NumberViewModel by lazy {
//        val repository = Repository()
//        val factory = NumberViewModel.provideFactory(repository, this)
//        ViewModelProvider(this, factory)[NumberViewModel::class.java]
//    }
    private val randomNumber : RandomNumber by lazy{
        RandomNumber()
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter

        randomNumber.randomNumberLiveData.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
        randomNumber.startGeneratingRandomNumbers()
        // viewModel.getRandomNumbers()


//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.numbers.collect { list ->
//                    Log.i("TEG", list.toString())
//                    adapter.submitList(list)
//                }
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        randomNumber.stopGeneratingRandomNumbers()
    }
}