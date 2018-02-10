package com.haohao.lazy.english.lazyenglish.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;



import org.json.JSONArray;

public class PreferencesUtil {
	private final static String Pref = "config";
    /**广播显示值存储的key**/
	public static final String BROADCAST_ID="broadcast_id";
	/**防沉迷广播显示值存储的key**/
	public static final String BROADCAST_ID_ANTI="broadcast_id_anti";
	/**
	 *  获得是否加速key
	 */
	public static final String IS_SPEED="is_speed";
	/**
	 *  获取上次加速倍数key
	 */
	public static final String SPEED_VALUE="speed_value";
	/**
	 *
	 * @param context 上下文
	 * @param key    key值
	 * @param value  默认值
      */
	public static void put(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(Pref,
				Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();

	}

	public static void put(Context context, String key, long value) {
		SharedPreferences sp = context.getSharedPreferences(Pref,
				Context.MODE_PRIVATE);
		sp.edit().putLong(key, value).commit();

	}

	public static SharedPreferences put(Context context, String key, int value) {
		SharedPreferences sp = context.getSharedPreferences(Pref,
				Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();
		return sp;
	}

	public static SharedPreferences put(Context context, String key, float value) {
		SharedPreferences sp = context.getSharedPreferences(Pref,
				Context.MODE_PRIVATE);
		sp.edit().putFloat(key, value).commit();
		return sp;
	}

	/**
	 *
	 * @param context
	 * @param key
	 * @param value
     * @return
     */
	public static SharedPreferences put(Context context, String key,
                                        boolean value) {
		SharedPreferences sp = context.getSharedPreferences(Pref,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
		return sp;
	}
	public static String getString(Context context, String key, String def) {

		return context.getSharedPreferences(Pref, Context.MODE_PRIVATE)
				.getString(key, def);
	}
    public static void remove(Context context, String key) {
         context.getSharedPreferences(Pref, Context.MODE_PRIVATE)
                .edit().remove(key).commit();
    }


	public static int getInt(Context context, String key, int def) {
		return context.getSharedPreferences(Pref, Context.MODE_PRIVATE)
				.getInt(key, def);
	}

	public static long getLong(Context context, String key, long def) {
		return context.getSharedPreferences(Pref, Context.MODE_PRIVATE)
				.getLong(key, def);
	}

	public static Boolean getBoolean(Context context, String key, boolean def) {
		return context.getSharedPreferences(Pref, Context.MODE_PRIVATE)
				.getBoolean(key, def);
	}

	public static Float getFloat(Context context, String key, float def){
		return context.getSharedPreferences(Pref, Context.MODE_PRIVATE).getFloat(key,def);
	}

	/**
	 * 根据本地存储的轮播id来判断是否显示
	 * @param  context    上下文
	 * @param  jsonArray  轮播的jsonArray
	 */
	public  static  boolean shareBroadcast(Context context, JSONArray jsonArray) throws Exception {
		boolean show=false;
		String oldId = PreferencesUtil.getString(context, BROADCAST_ID, "");  // 查找存储的id
		StringBuffer stringBuffer=new StringBuffer();
		for (int i = 0; i < jsonArray.length(); i++) {
			stringBuffer.append(jsonArray.getJSONObject(i).getString("_id"));
		}
		String newId = stringBuffer.toString();
		LogUtil.e("FloatBallMgr","广播旧比对值："+oldId);
		LogUtil.e("FloatBallMgr","广播新比对值："+newId);
        if(TextUtils.isEmpty(oldId)){  // 没有值就显示
			PreferencesUtil.put(context,BROADCAST_ID,newId);
			show=true;
		}
		if(!TextUtils.isEmpty(oldId)&& !newId.equals(oldId)){   // 有值且新旧不一致才显示
			PreferencesUtil.put(context,BROADCAST_ID,newId);
			show=true;
		}
		return show;
	}

	/**
	 * 根据本地存储的轮播id来判断是否显示
	 * @param  context    上下文
	 * @param  newId
	 */
	public  static  boolean shareBroadcast(Context context, String newId){
		boolean show=false;
		String oldId = PreferencesUtil.getString(context, BROADCAST_ID_ANTI, "");  // 查找存储的id
		LogUtil.e("FloatBallMgr","防沉迷广播旧比对值："+oldId);
		LogUtil.e("FloatBallMgr","防沉迷广播新比对值："+newId);
		if(TextUtils.isEmpty(oldId)){  // 没有值就显示
			PreferencesUtil.put(context,BROADCAST_ID_ANTI,newId);
			show=true;
		}
		if(!TextUtils.isEmpty(oldId)&& !newId.equals(oldId)){   // 有值且新旧不一致才显示
			PreferencesUtil.put(context,BROADCAST_ID_ANTI,newId);
			show=true;
		}
		return show;
	}

}
