package com.bcq.net.wrapper.interfaces;

import java.util.List;

/**
 * @param <R>
 * @param <E>
 */
public interface IResult<R, E> {

    /**
     * @return 结果集
     */
    R getResult();

    /**
     * @return 额外信息
     */
    E getExtra();

    class WrapResult<R, E> implements IResult<R, E> {
        protected R result;
        protected E extra;

        public WrapResult(R result, E extra) {
            this.result = result;
            this.extra = extra;
        }

        @Override
        public R getResult() {
            return result;
        }

        @Override
        public E getExtra() {
            return extra;
        }
    }

    /**
     * 已经废弃,使用ObjResult<List<R>> 替代
     */
    @Deprecated
    class ListResult<R> extends WrapResult<List<R>, Boolean> {
        public ListResult(List<R> result, Boolean extra) {
            super(result, extra);
        }
    }

    /**
     * 数据集结果封装
     *
     * @param <R>
     */
    class ObjResult<R> extends WrapResult<R, IPage> {
        public ObjResult(R result, IPage page) {
            super(result, page);
        }
    }


    /**
     * 状态结果封装
     */
    class StatusResult extends WrapResult<Integer, String> {
        public StatusResult(Integer result, String extra) {
            super(result, extra);
        }
    }
}
