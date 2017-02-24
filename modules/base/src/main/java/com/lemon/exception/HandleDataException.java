package com.lemon.exception;

/**
 *
 * 服务器处理数据出错，客户端需重新请求
 *
 * Created by Administrator on 2016/6/17.
 */
public class HandleDataException extends LemonServiceException{
      public HandleDataException(String msg){
          super(msg);
      }
}
