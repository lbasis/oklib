package com.bcq.net.wrapper.interfaces;

import android.content.DialogInterface;

/**
 * 加载等待框接口
 */
public interface ILoadTag {

    void show();

    void dismiss();

    String getTagMsg();

    ILoadTag setOnDismissListener(DialogInterface.OnDismissListener onDismiss);
}
