package com.example.OnlineStore.entity;

import com.example.OnlineStore.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseMessage<T> {
    T result;
    ResultCode resultCode;
    String details;
    Integer detailCode;

    public ResponseMessage(T result, ResultCode resultCode) {
        this.result = result;
        this.resultCode = resultCode;
    }

    public ResponseMessage(T result, ResultCode resultCode, String details) {
        this.result = result;
        this.resultCode = resultCode;
        this.details = details;
    }



    public ResponseMessage(T result, ResultCode resultCode, String details, Integer detailCode) {
        this.result = result;
        this.resultCode = resultCode;
        this.details = details;
        this.detailCode = detailCode;
    }

        public ResponseMessage(T result, ResultCode resultCode, Integer detailCode) {
            this.result = result;
            this.resultCode = resultCode;
            this.detailCode = detailCode;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "result=" + result +
                ", resultCode=" + resultCode +
                ", details='" + details + '\'' +
                ", detailCode=" + detailCode +
                '}';
    }
}
