package com.crud.group.web.controller;

import com.crud.group.web.controller.common.HttpResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseBodyModifier implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if(body instanceof HttpResponse httpResponse){

            if(httpResponse.isSuccessFull()){
                return ResponseEntity.ok(body);
            }else if(httpResponse.isClientError()){
                return ResponseEntity.badRequest().body(body);
            }else if(httpResponse.isServerError()){
                return ResponseEntity.internalServerError().body(body);
            }
        }

        throw new UnsupportedOperationException();

    }
}
