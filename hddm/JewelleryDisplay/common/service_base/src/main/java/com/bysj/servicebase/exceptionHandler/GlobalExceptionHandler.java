package com.bysj.servicebase.exceptionHandler;


import com.bysj.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R exceptions(Exception e){
        e.printStackTrace();
        return R.failed().message("执行了全局异常处理...");
    }
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回数据
    public R exceptions(ArithmeticException e){
        e.printStackTrace();
        return R.failed().message("执行了ArithmeticException异常处理...");
    }
    @ExceptionHandler(ZDYException.class)
    @ResponseBody//为了返回数据
    public R exceptions(ZDYException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.failed().code(e.getCode()).message(e.getMsg());
    }

}
