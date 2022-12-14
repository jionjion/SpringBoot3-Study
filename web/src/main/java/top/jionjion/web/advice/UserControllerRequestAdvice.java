package top.jionjion.web.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 对请求进行加强,仅有在Post/Put请求中有效
 * 默认只是对当前同包和子包进行处理
 *
 * @author Jion
 */
@ControllerAdvice
public class UserControllerRequestAdvice extends RequestBodyAdviceAdapter {

    final Logger logger = Logger.getLogger(UserControllerRequestAdvice.class.getName());

    /**
     * 该方法用于判断当前请求, 是否要执行 beforeBodyRead 方法和 afterBodyRead 或 handleEmptyBody , 会在他们两个之前各执行一次
     *
     * @param methodParameter 请求调用方法的参数对象
     * @param targetType      请求调用方法的参数类型
     * @param converterType   将会使用到的Http消息转换器类类型
     * @return 返回true 则会执行beforeBodyRead
     */
    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        logger.info(() -> "被拦截的方法: " + methodParameter.getMethod());
        logger.info(() -> "输出响应的Java类型为: " + targetType.getTypeName());
        logger.info(() -> "使用的响应转换器为: " + converterType.getName());
        return true;
    }


    /**
     * 在Http消息转换器执转换, 之前执行
     *
     * @param inputMessage  客户端的请求数据
     * @param parameter     请求调用方法的参数对象
     * @param targetType    请求调用方法的参数类型
     * @param converterType 将会使用到的Http消息转换器类类型
     * @return 返回 一个自定义的HttpInputMessage
     */
    @Override
    @NonNull
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        try (InputStream inputStream = inputMessage.getBody()) {
            logger.info(() -> "转化前, 当前输入内容: " + inputStream);
            logger.info(() -> "转化前, 当前参数类型: " + parameter.getParameterType());
            logger.info(() -> "转化前, 输出响应的Java类型为: " + targetType.getTypeName());
            logger.info(() -> "转化前, 使用的响应转换器为: " + converterType.getName());

            // 读取请求体
            byte[] body = inputMessage.getBody().readAllBytes();

            logger.info(() -> "读取到内容: " + new String(body));

            // 构造新的读取流, 替换原有请求流
            return new HttpInputMessage() {
                @Override
                @NonNull
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }

                @Override
                @NonNull
                public InputStream getBody() {
                    return new ByteArrayInputStream(body);
                }
            };
        }
    }

    /**
     * 在Http消息转换器执转换, 之后执行.一般是转换后的Java类, 用来封装参数
     *
     * @param body          转换后的对象
     * @param inputMessage  客户端的请求数据
     * @param parameter     请求调用方法的参数类型
     * @param targetType    请求调用方法的参数类型
     * @param converterType 使用的Http消息转换器类类型
     * @return 返回一个新的对象
     */
    @Override
    public @NonNull Object afterBodyRead(@NonNull Object body, @NonNull HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {

        logger.info(() -> "转化后, 生成的封装类: " + body.getClass().getName());
        logger.info(() -> "转化后, 消息HttpInputMessage封装类: " + inputMessage.hashCode());
        logger.info(() -> "转化后, 当前参数类型: " + parameter.getParameterType());
        logger.info(() -> "转化后, 输出响应的Java类型为: " + targetType.getTypeName());
        logger.info(() -> "转化后, 使用的响应转换器为: " + converterType.getName());

        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    /**
     * 在Http消息转换器执转换, 之后执行,
     * 不过这个方法处理的是, body为空的情况
     *
     * @param body          转换后的对象
     * @param inputMessage  客户端的请求数据
     * @param parameter     请求调用方法的参数类型
     * @param targetType    请求调用方法的参数类型
     * @param converterType 使用的Http消息转换器类类型
     * @return 返回一个新的对象
     */
    @Override
    @Nullable
    public Object handleEmptyBody(@Nullable Object body, @NonNull HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        logger.info(() -> "转化后且为空, " + Objects.isNull(body));
        logger.info(() -> "转化后且为空, 内容" + body);
        logger.info(() -> "转化后且为空, 消息HttpInputMessage封装类: " + inputMessage.hashCode());
        logger.info(() -> "转化后且为空, 当前参数类型: " + parameter.getParameterType());
        logger.info(() -> "转化后且为空, 输出响应的Java类型为: " + targetType.getTypeName());
        logger.info(() -> "转化后且为空, 使用的响应转换器为: " + converterType.getName());

        logger.info(() -> "handleEmptyBody " + body);
        return super.handleEmptyBody(body, inputMessage, parameter, targetType, converterType);
    }
}
