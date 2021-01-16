package com.dzakyhdr.academyjeptackpro.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.academyjeptackpro.R
import com.dzakyhdr.academyjeptackpro.data.ContentEntity
import com.dzakyhdr.academyjeptackpro.data.ModuleEntity
import com.dzakyhdr.academyjeptackpro.databinding.FragmentModuleContentBinding
import com.dzakyhdr.academyjeptackpro.ui.academy.viewmodel.ViewModelFactory
import com.dzakyhdr.academyjeptackpro.ui.reader.CourseReaderViewModel
import kotlinx.android.synthetic.main.fragment_module_content.*

class ModuleContentFragment : Fragment() {

    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }

    private lateinit var binding: FragmentModuleContentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentModuleContentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getSelectedModule().observe(viewLifecycleOwner, { module ->
                binding.progressBar.visibility = View.GONE
                if (module != null) {
                    populateWebView(module)
                }
            })
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        web_view.loadData(module.contentEntity!!.content, "text/html", "UTF-8")
    }


}