package com.bcq.net;

import com.bcq.net.api.Method;
import com.bcq.net.api.ORequest;
import com.bcq.net.wrapper.interfaces.BusiCallback;
import com.bcq.net.wrapper.GeneralWrapperCallBack;
import com.bcq.net.wrapper.interfaces.ILoadTag;
import com.bcq.net.wrapper.interfaces.IPage;
import com.bcq.net.wrapper.interfaces.IParse;
import com.bcq.net.wrapper.interfaces.IResult;

import java.util.List;
import java.util.Map;


/**
 * @author: BaiCQ
 * @ClassName: Request
 * @Description: 网络请求工具类
 * request:
 * 请求获取数据相关api 返回数据集
 * status：
 * 提交操作相关api 返回状态
 */
public class Request {

    public static ORequest status(ILoadTag tag,
                                  String url,
                                  Map<String, Object> params,
                                  Method method,
                                  BusiCallback<IResult.StatusResult, Integer, String, Void> iCallback) {
        return request(url,
                params,
                method,
                new GeneralWrapperCallBack(tag, null, iCallback));
    }

    public static <R> ORequest request(ILoadTag tag,
                                       String url,
                                       Map<String, Object> params,
                                       Method method,
                                       BusiCallback<IResult.ObjResult<List<R>>, List<R>, IPage, R> busiCallback) {
        return request(url,
                params,
                method,
                new GeneralWrapperCallBack(tag, null, busiCallback));
    }

    /**
     * @param tag          load视图
     * @param url          地址
     * @param params       参数
     * @param parser       自定义解析器
     * @param method       Method get/post
     * @param busiCallback 数据集回调
     * @return 请求封装体
     */
    public static <R> ORequest request(ILoadTag tag,
                                       String url,
                                       Map<String, Object> params,
                                       IParse parser,
                                       Method method,
                                       BusiCallback<IResult.ObjResult<List<R>>, List<R>, IPage, R> busiCallback) {
        return request(url,
                params,
                method,
                new GeneralWrapperCallBack(tag, parser, busiCallback));
    }

    /**
     * @param tag       load视图
     * @param url       地址
     * @param params    参数
     * @param parser    自定义解析器
     * @param method    Method get/post
     * @param iCallback 状态回调
     * @return 请求封装体
     */
    public static ORequest status(ILoadTag tag,
                                  String url,
                                  Map<String, Object> params,
                                  IParse parser,
                                  Method method,
                                  BusiCallback<IResult.StatusResult, Integer, String, Void> iCallback) {
        return request(url,
                params,
                method,
                new GeneralWrapperCallBack(tag, parser, iCallback));
    }

    public static <R> ORequest requestAgain(ORequest<R> request,
                                            BusiCallback<IResult.ObjResult<List<R>>, List<R>, IPage, R> busiCallback) {
        if (request.callBack instanceof GeneralWrapperCallBack) {
            ((GeneralWrapperCallBack) request.callBack).setBsiCallback(busiCallback);
        }
        return request.request();
    }

    private static <IR extends IResult<R, E>, R, E, T> ORequest request(String url,
                                                                        Map<String, Object> params,
                                                                        Method method,
                                                                        GeneralWrapperCallBack<IR, R, E, T> generalCallBack) {
        return ORequest.Builder.method(method)
                .url(url)
                .param(params)
                .callback(generalCallBack)
                .build()
                .request();
    }
}
