package com.bcq.net;


import com.bcq.net.api.OCallBack;
import com.bcq.net.wrapper.OkHelper;
import com.bcq.net.wrapper.OkUtil;
import com.bcq.net.wrapper.Wrapper;
import com.bcq.net.wrapper.interfaces.IParse;
import com.bcq.net.wrapper.interfaces.IWrap;

import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 封装简易回调
 */
public abstract class WrapperCallBack<T extends IWrap> extends OCallBack<T> {
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onBefore(Request.Builder builder) {
        if (null != OkHelper.get().getHeadCacher()) { //添加header
            Map<String, String> hs = OkHelper.get().getHeadCacher().onAddHeader();
            if (null == hs || hs.isEmpty()) return;
            for (Map.Entry<String, String> en : hs.entrySet()) {
                builder.addHeader(en.getKey(), en.getValue());
            }
        }
    }

    @Override
    public T onParse(Response response) throws Exception {
        String string = response.body().string();
        int httpCode = response.code();
        OkUtil.e("Wrapper", "string = " + string);
        //使用默认解析器
        IParse parser = OkHelper.get().getParser();
        if (null != parser) {
            return (T) parser.parse(httpCode, string);
        }
        // 默认解析失败，使用构建默认Wrapper
        Wrapper wrapper = new Wrapper();
        wrapper.setCode(httpCode);
        return (T) wrapper;
    }

    @Override
    public void onProgress(float progress, long total) {
    }

    @Override
    public void onAfter() {
    }

    @Override
    public abstract void onResult(T result);

    @Override
    public void onError(int code, String msg) {
        OkUtil.e(TAG, "onError:[" + code + "] message = " + msg);
    }
}
