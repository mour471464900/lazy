package com.haohao.lazy.english.lazyenglish.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.haohao.lazy.english.lazyenglish.R;
import com.haohao.lazy.english.lazyenglish.utils.LogUtil;


/**
 * 圆角FrameLayout
 * 将任意视图view放入其中后能完成圆角的需求
 *
 * @author wuhao
 *         <p>
 *         <declare-styleable name="RoundAngleFrameLayout">
 *         <attr name="radius" format="dimension" />
 *         <attr name="topLeftRadius" format="dimension" />
 *         <attr name="topRightRadius" format="dimension" />
 *         <attr name="bottomLeftRadius" format="dimension" />
 *         <attr name="bottomRightRadius" format="dimension" />
 *         </declare-styleable>
 *         </p>
 */
public class RoundAngleFrameLayout extends FrameLayout {

    public static final String TAG = "RoundAngleFrameLayout";

    private float topLeftRadius;
    private float topRightRadius;
    private float bottomLeftRadius;
    private float bottomRightRadius;

    private Paint roundPaint;
    private Paint imagePaint;


    public RoundAngleFrameLayout(Context context) {
        this(context, null);
    }

    public RoundAngleFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

//      attrs 文件示例
//    <declare-styleable name="RoundAngleFrameLayout">
//    <attr name="radius" format="dimension" />
//    <attr name="topLeftRadius" format="dimension" />
//    <attr name="topRightRadius" format="dimension" />
//    <attr name="bottomLeftRadius" format="dimension" />
//    <attr name="bottomRightRadius" format="dimension" />
//    </declare-styleable>

    @SuppressLint("ResourceType")
    public RoundAngleFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String attrName = attrs.getAttributeName(i);
            String attrVal = attrs.getAttributeValue(i);
            LogUtil.e(TAG, "attrName = " + attrName + " , attrVal = " + attrVal);
        }
        if (attrs != null) {
//           由于R文件的原因，目前只 支持app：radius 属性
            @SuppressLint("CustomViewStyleable")
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundRelativeLayout);
            float radius = ta.getDimension(R.styleable.RoundRelativeLayout_radius, 0);    //   对应属性数组下标
            topLeftRadius = ta.getDimension(R.styleable.RoundRelativeLayout_topLeftRadius, radius);
            topRightRadius = ta.getDimension(R.styleable.RoundRelativeLayout_topRightRadius, radius);
            bottomLeftRadius = ta.getDimension(R.styleable.RoundRelativeLayout_bottomLeftRadius, radius);
            bottomRightRadius = ta.getDimension(R.styleable.RoundRelativeLayout_bottomRightRadius, radius);
            ta.recycle();
        }
        roundPaint = new Paint();
        roundPaint.setColor(Color.WHITE);
        roundPaint.setAntiAlias(true);
        roundPaint.setStyle(Paint.Style.FILL);
        roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        imagePaint = new Paint();
        imagePaint.setXfermode(null);
        LogUtil.e(TAG, "topLeftRadius:" + topLeftRadius +
                "  topRightRadius：" + topRightRadius + "  bottomLeftRadius：" + bottomLeftRadius + "  bottomRightRadius：" + bottomRightRadius);
    }

    //实现4
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), imagePaint, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
//        只能手动不支持 左上角圆角  和  右上角圆角
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottomLeft(canvas);
        drawBottomRight(canvas);
        canvas.restore();
    }

    private void drawTopLeft(Canvas canvas) {
        if (topLeftRadius > 0) {
            Path path = new Path();
            path.moveTo(0, topLeftRadius);
            path.lineTo(0, 0);
            path.lineTo(topLeftRadius, 0);
            path.arcTo(new RectF(0, 0, topLeftRadius * 2, topLeftRadius * 2),
                    -90, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawTopRight(Canvas canvas) {
        if (topRightRadius > 0) {
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - topRightRadius, 0);
            path.lineTo(width, 0);
            path.lineTo(width, topRightRadius);
            path.arcTo(new RectF(width - 2 * topRightRadius, 0, width,
                    topRightRadius * 2), 0, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawBottomLeft(Canvas canvas) {
        if (bottomLeftRadius > 0) {
            int height = getHeight();
            Path path = new Path();
            path.moveTo(0, height - bottomLeftRadius);
            path.lineTo(0, height);
            path.lineTo(bottomLeftRadius, height);
            path.arcTo(new RectF(0, height - 2 * bottomLeftRadius,
                    bottomLeftRadius * 2, height), 90, 90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawBottomRight(Canvas canvas) {
        if (bottomRightRadius > 0) {
            int height = getHeight();
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - bottomRightRadius, height);
            path.lineTo(width, height);
            path.lineTo(width, height - bottomRightRadius);
            path.arcTo(new RectF(width - 2 * bottomRightRadius, height - 2
                    * bottomRightRadius, width, height), 0, 90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    public float getTopLeftRadius() {
        return topLeftRadius;
    }

    public void setTopLeftRadius(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
    }

    public float getTopRightRadius() {
        return topRightRadius;
    }

    public void setTopRightRadius(float topRightRadius) {
        this.topRightRadius = topRightRadius;
    }

    public float getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    public void setBottomLeftRadius(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
    }

    public float getBottomRightRadius() {
        return bottomRightRadius;
    }

    public void setBottomRightRadius(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
    }

    /**
     * 移除圆角
     */
    public void removeRound() {
        setTopLeftRadius(0);
        setTopRightRadius(0);
        setBottomRightRadius(0);
        setBottomLeftRadius(0);
    }


}
