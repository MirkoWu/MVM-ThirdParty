package com.mirkowu.mvm

import android.content.Context
import android.content.Intent
import android.util.Log
import com.mirkowu.lib_base.mediator.EmptyMediator
import com.mirkowu.lib_base.util.bindingView
import com.mirkowu.lib_bugly.BuglyManager
import com.mirkowu.lib_util.ktxutil.click
import com.mirkowu.lib_util.utilcode.util.BarUtils
import com.mirkowu.lib_util.utilcode.util.ToastUtils
import com.mirkowu.lib_webview.CommonWebActivity
import com.mirkowu.mvm.base.BaseActivity

class MainActivity : BaseActivity<EmptyMediator>() {
    companion object {
        fun start(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            context.startActivity(starter)
        }
    }

    val binding by bindingView(com.mirkowu.mvm.databinding.ActivityMainBinding::inflate)

    override fun initMediator(): EmptyMediator {
        return super.initMediator()
    }

    override fun initStatusBar() {
        super.initStatusBar()
        BarUtils.transparentStatusBar(this)
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun initialize() {
        binding.btnJumpMvm.click {
            CommonWebActivity.start(this,"","https://github.com/MirkoWu/MVM")
        }
        binding.btnBuglyUpgrade.click {
            showLoadingDialog("检查新版本")
            BuglyManager.checkUpgrade { hasNewVersion, upgradeInfo ->
                Log.e("BuglyManager", "setUpgradeListener:   upgradeInfo=$upgradeInfo  ")
                hideLoadingDialog()
                if (upgradeInfo != null) {
                    com.mirkowu.lib_bugly.UpgradeDialog.show(supportFragmentManager, upgradeInfo)
                } else {
                    ToastUtils.showShort("当前已是最新版本!")
                }
            }
        }
        binding.btnUpgrade.click {
            // BuglyManager.checkUpgrade(true, false)
            val url =
                "https://outexp-beta.cdn.qq.com/outbeta/2021/06/18/commirkowumvm_1.0.1_56987f9a-fb39-56d5-9ac4-a4c055633672.apk"
            BuglyManager.checkUpgrade { hasNewVersion, upgradeInfo ->
                Log.e("BuglyManager", "setUpgradeListener:   upgradeInfo=$upgradeInfo  ")


                if (upgradeInfo != null) {
                    com.mirkowu.lib_bugly.UpgradeDialog.show(supportFragmentManager, upgradeInfo)
                } else {
                    ToastUtils.showShort("当前已是最新版本!")
                }
            }

        }

        binding.btnCrash.click {
            throw RuntimeException("测试BUG")
        }

    }


    override fun onDestroy() {
        BuglyManager.removeUpgradeListener()
        super.onDestroy()
    }

}