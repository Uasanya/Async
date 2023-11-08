package com.example.async

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.async.databinding.FragmentNumberBinding


class NumberFragment : Fragment() {

    private var _binding: FragmentNumberBinding? = null
    private val binding get() = _binding!!
    private val list = arrayListOf<Int>()
    private val adapter: NumberAdapter = NumberAdapter()
    private val viewModel: NumberViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter
        viewModel.getRandomNumbers(
        )
        viewModel.randomNumberLiveData.observe(viewLifecycleOwner) { number ->
            Log.i("TEG", number.toString())
            list.add(number)
            adapter.submitList(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}