package com.bcq.net.wrapper.interfaces;

import java.io.Serializable;

/**
 * @author: BaiCQ
 * @ClassName: IPage
 * @date: 2018/6/27
 * @Description: result的分页信息接口
 */
public interface IPage extends Serializable {
    int getPage();

    int getTotal();
}
