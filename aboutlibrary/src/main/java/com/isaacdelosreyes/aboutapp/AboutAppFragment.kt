package com.isaacdelosreyes.aboutapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import com.isaacdelosreyes.aboutapp.utils.*
import com.isaacdelosreyes.aboutapp.utils.extensions.findPreferenceByKey
import com.isaacdelosreyes.aboutapp.utils.extensions.getAppName
import com.isaacdelosreyes.aboutapp.utils.extensions.openDetailAppSettings
import com.isaacdelosreyes.aboutapp.utils.extensions.shareLink
import com.isaacdelosreyes.aboutapp.views.PolicyDialogFragment

class AboutAppFragment : PreferenceFragmentCompat() {

    companion object {

        fun newInstance() = AboutAppFragment()
    }

    //region override methods

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_about_app, rootKey)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openPrivacyPolicy()
        rateApplicationInGooglePlay()
        sendEmailToDeveloper()
        initFooterValues()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.about_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_app_info -> {
                activity?.openDetailAppSettings()
                true
            }
            R.id.action_share -> {
                val marketUri = GOOGLE_PLAY_ENDPOINT + context?.packageName
                context?.shareLink(
                    getString(
                        R.string.share_app_content,
                        context?.getAppName(),
                        marketUri
                    )
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //endregion

    //region private methods

    private fun rateApplicationInGooglePlay() {
        val rateThisApp = findPreferenceByKey(PREF_RATE_APP)
        rateThisApp?.setOnPreferenceClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    GOOGLE_PLAY_ENDPOINT
                            + context?.packageName
                )
            )
            startActivity(browserIntent)
            true
        }
    }

    private fun openPrivacyPolicy() {
        val openPolicyDialog = findPreferenceByKey(PREF_OPEN_POLICY)
        openPolicyDialog?.setOnPreferenceClickListener {
            val policyDialog = PolicyDialogFragment.newInstance()
            activity?.supportFragmentManager?.let { safeFragmentManager ->
                policyDialog.show(safeFragmentManager, EMPTY_STRING)
            }
            true
        }
    }

    private fun initFooterValues() {
        val footerLabel = findPreferenceByKey(PREF_FOOTER_COPYRIGHT)
        footerLabel?.title = getString(R.string.developer_copyright)
    }

    private fun sendEmailToDeveloper() {
        val emailToDeveloper = findPreferenceByKey(SEND_EMAIL_TO_DEV)
        emailToDeveloper?.setOnPreferenceClickListener {
            sendReportEmail()
            true
        }
    }

    private fun sendReportEmail() {
        val selectorIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
        }
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(
                Intent.EXTRA_EMAIL, arrayOf(
                    getString(R.string.email_contact_summary)
                )
            )
            selector = selectorIntent
        }
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }

    //endregion
}