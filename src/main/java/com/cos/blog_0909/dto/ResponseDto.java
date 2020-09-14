package com.cos.blog_0909.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder              //type 
public class ResponseDto<T> {
int status;
T Data;
}
