package com.example.newsapp.ui.home.news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.api.model.sourceResponse.Source
import com.example.newsapp.ui.showMessage
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.ViewError
import com.example.newsapp.ui.home.news.newsDetails.NewsDetailsActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    lateinit var binding: FragmentNewsBinding
    lateinit var viewModel: NewsViewModel
     private var newsSource : Source = Source()
    var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        viewModel.getNewsSources()
    }

    private fun initObservers() {


        viewModel.sourcesLiveData.observe(viewLifecycleOwner){
            bindTabs(it)
            getNewsOnScroll()
        }

        viewModel.newsLiveData.observe(viewLifecycleOwner){
            adapter.bindView(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            handelError(it)
        }
    }

    private fun getNewsOnScroll() {
        viewModel.getNews(newsSource.id)
        isLoading = false
    }

   @Inject lateinit var  adapter : NewsAdapter

    private fun initViews() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        val lastVisibleItemCount = layoutManager.findLastVisibleItemPosition()
        val totalItemCount  = layoutManager.itemCount
        val visibleThresholds = 3

        if(!isLoading && totalItemCount - lastVisibleItemCount <= visibleThresholds){
            Log.d("test" , totalItemCount.toString())
            Log.d("lasTtest" , lastVisibleItemCount.toString())
          isLoading = true
       //     page++
            getNewsOnScroll()
        }

        adapter.onNewsClickListener = NewsAdapter.OnNewsClickListener {
            val intent = Intent(requireContext(),NewsDetailsActivity::class.java)
            intent.putExtra("news",it)
            startActivity(intent)
        }
    }



    private fun bindTabs(sources: List<Source?>?) {

        if (sources == null) return
        sources.forEach { source ->
            val tab = binding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            newsSource = source!!
            binding.tabLayout.addTab(tab)
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                viewModel.getNews(source.id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                viewModel.getNews(source.id )
            }

        })
       binding.tabLayout.getTabAt(0)?.select()

    }

    private fun handelError(viewError: ViewError){
        showMessage(
            message = viewError.message ?: viewError.throwable?.localizedMessage ?: "Something Went Wrong",
            posActionName = "Try Again",
            posAction = { dialog, which ->
                dialog.dismiss()
                viewError.onTryAgainClickListener?.onTryAgainClick()
            },
            negActionName = "Cancel",
            negAction = { dialog, which ->
                dialog.dismiss()
            }
        )
    }
    }

