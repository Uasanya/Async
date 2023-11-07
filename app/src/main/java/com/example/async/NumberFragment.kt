package com.example.async

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.async.databinding.FragmentNumberBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NumberFragment : Fragment() {

    private var _binding: FragmentNumberBinding? = null
    private val binding get() = _binding!!
    private var repository: Repository = Repository()
    private val adapter: NumberAdapter = NumberAdapter()
    private val viewModel : NumberViewModel by lazy{
       repository  = Repository()
        ViewModelProvider(this)[NumberViewModel::class.java]
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
        viewModel.getRandomNumbers()
        viewModel.randomNumberLiveData.observe(viewLifecycleOwner) { number ->
            val list = listOf<Int>(number)
            adapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}