package com.bcq.net.net;

import com.bcq.net.wrapper.OkUtil;
import com.bcq.net.wrapper.interfaces.BusiCallback;
import com.bcq.net.wrapper.interfaces.IResult;

/**
 * @author: BaiCQ
 * @ClassName: StatusCallback
 * @Description: 没有body请求回调
 */
public class StatusCallback implements BusiCallback<IResult.StatusResult, Integer, String, Void> {
    protected final String TAG = this.getClass().getSimpleName();

    public StatusCallback() {
    }

    /**
     * @param result
     */
    @Override
    public void onResult(IResult.StatusResult result) {
    }

    /**
     * @param code 错误码
     * @param msg  错误信息
     */
    @Override
    public void onError(int code, String msg) {
        OkUtil.e(TAG, "onError:[" + code + "] message = " + msg);
    }

    @Override
    public void onAfter() {
    }

    @Override
    public Class<Void> onGetType() {
        return null;
    }
}
