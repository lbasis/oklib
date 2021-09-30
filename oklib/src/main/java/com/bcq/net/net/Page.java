package com.bcq.net.net;

public interface Page {
    /**
     * 首页页的索引 0 或者 1
     */
    int getFirstIndex();

    /**
     * @return 分页设置的每页的记录数 如：15条
     */
    int geSize();

    /**
     * 参数中页码的key
     */
    String getKeyPage();

    /**
     * 参数中每页记录数的key
     */
    String getKeySize();
}
