package com.example.async

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.async.databinding.FragmentNumberBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class NumberFragment : Fragment() {

    private val disposable = CompositeDisposable()
    private val randomNumber = RandomNumber()
    private var _binding: FragmentNumberBinding? = null
    private val binding get() = _binding!!
    private val adapter: NumberAdapter = NumberAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter
        val numberObservable = randomNumber.generateNumbersList().observeOn(AndroidSchedulers.mainThread())
        disposable.add(
            numberObservable.subscribe(){list ->
                adapter.submitList(list)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposable.clear()
    }
}