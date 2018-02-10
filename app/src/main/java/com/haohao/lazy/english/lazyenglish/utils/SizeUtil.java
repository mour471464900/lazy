package com.haohao.lazy.english.lazyenglish.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SizeUtil {

	
	/**
	 * px转dip 像素转独立像素
	 * */
	public static int px2dip(Context context, float pxValue) {
		// 屏幕密度
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * dip转px 独立像素转像素
	 * */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		Log.e("SizeUtil","scale:"+scale);
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */

	public static int px2sp(Context context, float pxValue) {

		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;

		return (int) (pxValue / fontScale + 0.5f);

	}
	
	  /** 
     * 将sp值转换为px值，保证文字大小不变 
     *  
     * @param spValue 
     * @param fontScale 
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */  
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (spValue * fontScale + 0.5f);  
    }

//   转换图片尺寸
	public static int getProportionHeight(int  width) {//图片原始尺寸  w 650   h 540

		float scale = (float)width/650;
//		int newW = 0;
//        int newH = 0;
//        if(scaleX > scaleY){
		return   (int) (540 * scale);//1:3   240  720
//		return (.widthPixels * (h * 100.0f / w) / 1int) (App00.0f);
	}

	//   转换图片尺寸
	public static int getProportionWidth(int  height) {//图片原始尺寸  w 650  h 295

		float scale = (float)height/540;
//		int newW = 0;
//        int newH = 0;
//        if(scaleX > scaleY){
		return   (int) (650 * scale);//1:3   240  720
//		return (.widthPixels * (h * 100.0f / w) / 1int) (App00.0f);
	}

	/**
	 * 設置View的寬度（像素），若設置爲自適應則應該傳入MarginLayoutParams.WRAP_CONTENT
	 * @param view
	 * @param width
	 */
	public static void setLayoutWidth(View view, int width)
	{
		if (view.getParent() instanceof FrameLayout){
			FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams) view.getLayoutParams();
			lp.width=width;
			view.setLayoutParams(lp);
			//view.setX(x);
			view.requestLayout();
		}
		else if (view.getParent() instanceof RelativeLayout){
			RelativeLayout.LayoutParams lp=(RelativeLayout.LayoutParams)view.getLayoutParams();
			lp.width=width;
			view.setLayoutParams(lp);
			//view.setX(x);
			view.requestLayout();
		}
		else if (view.getParent() instanceof LinearLayout){
			LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams)view.getLayoutParams();
			lp.width=width;
			view.setLayoutParams(lp);
			view.requestLayout();
		}
	}

	/**
	 * 設置View的高度（像素），若設置爲自適應則應該傳入MarginLayoutParams.WRAP_CONTENT
	 * @param view
	 * @param height
	 */
	public static void setLayoutHeight(View view, int height)
	{
		if (view.getParent() instanceof FrameLayout){
			FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams) view.getLayoutParams();
			lp.height=height;
			view.setLayoutParams(lp);
			view.requestLayout();
		}
		else if (view.getParent() instanceof RelativeLayout){
			RelativeLayout.LayoutParams lp=(RelativeLayout.LayoutParams)view.getLayoutParams();
			lp.height=height;
			view.setLayoutParams(lp);
			view.requestLayout();
		}
		else if (view.getParent() instanceof LinearLayout){
			LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams)view.getLayoutParams();
			lp.height=height;
			view.setLayoutParams(lp);
			view.requestLayout();
		}
	}
}
