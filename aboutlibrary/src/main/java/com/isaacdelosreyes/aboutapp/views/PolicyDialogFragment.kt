package com.isaacdelosreyes.aboutapp.views

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.DialogFragment
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.isaacdelosreyes.aboutapp.R
import com.isaacdelosreyes.aboutapp.databinding.FragmentDialogPolicyBinding
import com.isaacdelosreyes.aboutapp.utils.extensions.isDarkThemeOn
import com.isaacdelosreyes.aboutapp.utils.extensions.isTrue

private const val PADDING_START = 80
private const val PADDING_END = 80

class PolicyDialogFragment : DialogFragment() {

    private var binding: FragmentDialogPolicyBinding? = null

    companion object {

        fun newInstance() = PolicyDialogFragment()
    }

    //region override methods

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDialogStyle()
        binding = FragmentDialogPolicyBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUrl()
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        setDialogParams()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    //endregion

    //region private methods

    private fun setDialogStyle() {
        dialog?.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            decorView.setPadding(PADDING_START, 0, PADDING_END, 0)
            decorView.background = ColorDrawable(Color.TRANSPARENT)
        }
    }

    private fun setDialogParams() {
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    private fun loadUrl() {
        binding?.dialogPolicyWebViewMain?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        binding?.dialogPolicyWebViewMain?.loadUrl(
            getString(R.string.privacy_policy_link)
        )
    }

    private fun initListeners() {
        setOnCloseDialogListener()
        setOnOpenExternalListener()
    }

    private fun setOnOpenExternalListener() {
        binding?.dialogPolicyImgOpenInWeb?.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    getString(R.string.privacy_policy_link)
                )
            )
            startActivity(browserIntent)
        }
    }

    private fun setOnCloseDialogListener() {
        binding?.dialogPolicyLabelCloseDialog?.setOnClickListener {
            dismiss()
        }
    }

    //endregion
}