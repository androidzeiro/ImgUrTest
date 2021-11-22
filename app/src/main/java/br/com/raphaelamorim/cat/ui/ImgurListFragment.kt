package br.com.raphaelamorim.cat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import br.com.raphaelamorim.cat.databinding.FragmentCatListBinding
import br.com.raphaelamorim.cat.ui.adapter.ImagesAdapter
import br.com.raphaelamorim.cat.ui.base.BaseFragment
import br.com.raphaelamorim.cat.viewmodel.ImgurViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImgurListFragment : BaseFragment<FragmentCatListBinding>() {
    override val viewModel: ImgurViewModel by viewModels()
    private var adapter: ImagesAdapter? = null
    override fun onCreateBinding(inflater: LayoutInflater) = FragmentCatListBinding.inflate(inflater)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        setupAdapter()

        viewModel.searchImages("cats")
    }

    private fun setupAdapter() {
        adapter = ImagesAdapter()
        binding.rvImages.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }
        binding.rvImages.adapter = adapter
    }

    private fun registerObservers() {
        viewModel.imgurResult.observe(viewLifecycleOwner) {
            if(it == null) return@observe
            adapter?.setImages(it.images)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /**Avoid memory leak*/
        adapter = null
    }
}