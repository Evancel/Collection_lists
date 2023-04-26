package pro.sky.collection_l1.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BadParamsException extends RuntimeException{
    public BadParamsException(){
        super();
    }
}
