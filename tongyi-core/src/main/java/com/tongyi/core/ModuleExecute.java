package com.tongyi.core;

import java.util.function.BiFunction;

@FunctionalInterface
public interface ModuleExecute<T,U,R> extends BiFunction<T,U,R> {
    default R apply(T module, U params) throws ServiceException{
        R r = null;
        if (this.checker(module,params)) {
            r = this.execute(module,params);
            this.onFinish(module);
        }
        return r;
    }
    default boolean checker(T module, U params){
        return true;
    }

    public R execute(T module, U params) throws ServiceException;

    default void onFinish(T module){

    }
}
