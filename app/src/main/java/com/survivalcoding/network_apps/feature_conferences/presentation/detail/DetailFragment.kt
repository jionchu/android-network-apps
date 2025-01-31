package com.survivalcoding.network_apps.feature_conferences.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.survivalcoding.network_apps.feature_conferences.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.feature_conferences.presentation.ConferencesViewModel
import com.survivalcoding.network_apps.feature_conferences.presentation.ConferencesViewModelFactory
import android.content.Intent
import android.net.Uri
import com.survivalcoding.network_apps.databinding.FragmentDetailBinding
import com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.RemoteConferenceDataSource
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<ConferencesViewModel> {
        ConferencesViewModelFactory(ConferenceRepositoryImpl(RemoteConferenceDataSource()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // actionBar 설정
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            viewModel.state.value?.conference?.name

        viewModel.state.observe(viewLifecycleOwner) { state ->
            // 컨퍼런스 정보 표시
            binding.detailTvLocation.text = state.conference?.location
            binding.detailTvDuration.text =
                getDurationStr(state.conference?.start, state.conference?.end)
        }

        // 링크 클릭 시 웹사이트로 이동
        binding.detailTvLink.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(viewModel.state.value?.conference?.link)
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getDurationStr(start: String?, end: String?): String {
        return try {
            val strFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            "${
                DateFormat.getDateInstance(DateFormat.MEDIUM).format(strFormat.parse(start))
            } - ${DateFormat.getDateInstance(DateFormat.MEDIUM).format(strFormat.parse(end))}"
        } catch (e: Exception) {
            ""
        }
    }
}