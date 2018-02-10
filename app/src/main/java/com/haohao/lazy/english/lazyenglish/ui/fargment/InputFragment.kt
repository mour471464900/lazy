package com.haohao.lazy.english.lazyenglish.ui.fargment

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import com.haohao.lazy.english.lazyenglish.R
import com.haohao.lazy.english.lazyenglish.ui.view.ShotTypeDialog
import com.haohao.lazy.english.lazyenglish.utils.*
import com.iflytek.cloud.*
import kotlinx.android.synthetic.main.fragment_input.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *
 */
class InputFragment : BaseFragment() {
    // 语音合成对象
    private var mTts: SpeechSynthesizer? = null

    //    <string-array name="voicer_cloud_values">
//    <item>xiaoyan</item>
//    <item>xiaoyu</item>
//    <item>catherine</item>
//    <item>henry</item>
//    <item>vimary</item>
//    <item>vixy</item>
//    <item>xiaoqi</item>
//    <item>vixf</item>
//    <item>xiaomei</item>
//    <item>xiaolin</item>
//    <item>xiaorong</item>
//    <item>xiaoqian</item>
//    <item>xiaokun</item>
//    <item>xiaoqiang</item>
//    <item>vixying</item>
//    <item>xiaoxin</item>
//    <item>nannan</item>
//    <item>vils</item>
//    </string-array>
    // 默认发音人
    private var voicer = "xiaoqi"
    // 引擎类型
    private var mEngineType = SpeechConstant.TYPE_CLOUD
    private var voicePath: String = ""

    private lateinit var pd: ProgressDialog

    override fun getLayoutId(): Int {
        return R.layout.fragment_input
    }

    // 语音合成初始化监听
    private var mTtsInitListener = InitListener { code ->
        LogUtil.e("haohao", "语音合成初始化成功")
        when (code) {
            ErrorCode.SUCCESS -> {
                LogUtil.e("haohao", "语音合成初始化成功")
            }
            else -> showToast("语音合成初始化失败，错误码：" + code)
        }
    }


    override fun initView() {
        // 初始化合成对象
        pd = ProgressDialog(context)
        pd.max = 100
        pd.progress = 0
        pd.setProgressNumberFormat(null)
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        pd.setTitle("当前语音合成进度:")
        mTts = SpeechSynthesizer.createSynthesizer(context, mTtsInitListener)
        btn_cancel.setOnClickListener {
            editText.setText("")
        }
        btn_publish.setOnClickListener {
            if (null == mTts) {
                // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
                showToast("创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化")
                return@setOnClickListener
            }
            if (!AppUtil.isNetworkAvailable(context)) {
                showToast("无法连接当前网络")
                return@setOnClickListener
            }
            voicePath = FileManager.appTempFile.path + "/" + DateUtil.getLongFromat(System.currentTimeMillis()) + ".wav"
            setParam()
            val context = editText.text.toString().trim()
            val code = mTts?.startSpeaking(context, mTtsListener)
            when (code) {
                ErrorCode.SUCCESS -> if (pd != null) pd.show()
                else -> showToast("语音合成失败,错误码: " + code)
            }
        }
        rlChange.setOnClickListener {
            ShotTypeDialog(context).show()
        }
    }


    /**
     * 参数设置
     * @return
     */
    private fun setParam() {
        // 清空参数
        mTts?.setParameter(SpeechConstant.PARAMS, null)
        // 根据合成引擎设置相应参数
        if (mEngineType == SpeechConstant.TYPE_CLOUD) {
            mTts?.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD)
            // 设置在线合成发音人
            mTts?.setParameter(SpeechConstant.VOICE_NAME, voicer)
            //设置合成语速
            mTts?.setParameter(SpeechConstant.SPEED, "50")
            //设置合成音调
            mTts?.setParameter(SpeechConstant.PITCH, "50")
            //设置合成音量
            mTts?.setParameter(SpeechConstant.VOLUME, "50")
        } else {
            mTts?.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL)
            // 设置本地合成发音人 voicer为空，默认通过语记界面指定发音人。
            mTts?.setParameter(SpeechConstant.VOICE_NAME, "")
            /**
             * TODO 本地合成不设置语速、音调、音量，默认使用语记设置
             * 开发者如需自定义参数，请参考在线合成参数设置
             */
        }
        //设置播放器音频流类型
        mTts?.setParameter(SpeechConstant.STREAM_TYPE, "3")
        // 设置播放合成音频打断音乐播放，默认为true
        mTts?.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true")
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts?.setParameter(SpeechConstant.AUDIO_FORMAT, "wav")
        mTts?.setParameter(SpeechConstant.TTS_AUDIO_PATH, voicePath)
    }


    /**
     * 合成回调监听。
     */
    private val mTtsListener = object : SynthesizerListener {

        override fun onSpeakBegin() {
            LogUtil.e("haohao", "开始播放")
            mTts?.stopSpeaking()
        }

        override fun onSpeakPaused() {
            LogUtil.e("haohao", "暂停播放")
        }

        override fun onSpeakResumed() {
            LogUtil.e("haohao", "继续播放")
        }

        override fun onBufferProgress(percent: Int, beginPos: Int, endPos: Int,
                                      info: String) {
            LogUtil.e("haohao", "合成语音的进度:" + percent)
            // 合成进度
            if (pd != null) pd.progress = percent
            if (percent == 100) {
                pd.dismiss()
                showToast("语音合成完毕")

            }
        }

        override fun onSpeakProgress(percent: Int, beginPos: Int, endPos: Int) {
            // 播放进度
        }

        override fun onCompleted(error: SpeechError?) {
            if (error == null) {
                LogUtil.e("haohao", "播放完成")
            } else if (error != null) {
                showToast(error.getPlainDescription(true))
            }
        }

        override fun onEvent(eventType: Int, arg1: Int, arg2: Int, obj: Bundle) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (null != mTts) {
            mTts?.stopSpeaking()
            // 退出时释放连接
            mTts?.destroy()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onShotEvent(event: EventBusUtil.EssayChicoe) {
        if (!TextUtils.isEmpty(event.type)) editText.setText(event.type)
    }

}