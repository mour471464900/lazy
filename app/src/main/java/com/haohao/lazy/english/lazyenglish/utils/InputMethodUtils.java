package com.haohao.lazy.english.lazyenglish.utils;

import android.app.Activity;
import android.content.Context;
import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

public class InputMethodUtils {
	private static Runnable mShowImeRunnable;

	/**
	 * 设置一个view延迟显示或者隐藏输入法
	 * 
	 * @param visible
	 */
	public static void setViewInput(final View view, final boolean visible) {
		if (null == mShowImeRunnable) {
			mShowImeRunnable = new Runnable() {
				public void run() {
					InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

					if (imm != null) {
						showSoftInputUnchecked(view, imm, 0);
					}
				}
			};
		}
		if (visible) {
			view.post(mShowImeRunnable);
		} else {
			view.removeCallbacks(mShowImeRunnable);
			InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm != null) {
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
		}
	}

	/**
	 * 强制显示输入法
	 * 
	 * @param view
	 * @param imm
	 * @param flags
	 */
	private static void showSoftInputUnchecked(View view, InputMethodManager imm, int flags) {
		try {
			Method method = imm.getClass().getMethod("showSoftInputUnchecked", int.class, ResultReceiver.class);
			method.setAccessible(true);
			method.invoke(imm, flags, null);
		} catch (Exception e) {
			imm.showSoftInput(view, flags);
		}
	}

	/**
	 * 隐藏输入法
	 * 
	 * @param context
	 * @param view
	 */
	public static void hideSoftInput(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * 隐藏输入法
	 */
	public static void hideSoftInput(Activity activity) {
		if (null != activity && activity.getCurrentFocus() != null) {
			((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * toggleSoftInput
	 * 这个方法可以转换软件输入法在窗体中的显示状态。如果输入法当前是显示状态，那么该方法设置输入法隐藏。如果输入法当前是隐藏状态
	 * ，则该方法设置输入法显示。
	 * 
	 * @param context
	 */
	public static void showSoftInput(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
	}

	/**
	 * 判断输入法是否显示
	 * 
	 * @param paramsContext
	 * @return
	 */
	public static boolean isInput(Context paramsContext) {
		InputMethodManager imm = (InputMethodManager) paramsContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		return imm.isActive();// 若返回true，则表示输入法打开
	}




	//通过定时器强制隐藏虚拟键盘
	public static void TimerHideKeyboard(final View v)
	{
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm.isActive()) {
					imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
				}
			}
		}, 10);
	}

	//显示虚拟键盘
	public static void ShowKeyboard(View v)
	{
		InputMethodManager imm = (InputMethodManager) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );

		imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);

	}

	//延时0.3秒强制显示系统键盘
	public static void showKeyBoard(final EditText txtSearchKey)
	{

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager m = (InputMethodManager)
						txtSearchKey.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				m.showSoftInput(txtSearchKey, InputMethodManager.SHOW_FORCED);
			}
		}, 300);
	}
	//延时0.3秒强制隐藏系统键盘
	public static void hideKeyBoard(final EditText txtSearchKey)
	{
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run()
			{
				InputMethodManager m = (InputMethodManager)
						txtSearchKey.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				m.hideSoftInputFromWindow(txtSearchKey.getWindowToken(),0);
			}
		}, 300);
	}


	//直接强制隐藏系统键盘
	public static void hideKeyBoard2(final EditText txtSearchKey)
	{
		InputMethodManager m = (InputMethodManager)
				txtSearchKey.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		m.hideSoftInputFromWindow(txtSearchKey.getWindowToken(),0);
	}

}
