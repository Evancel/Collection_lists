package pro.sky.collection_l1;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BadParamsException extends RuntimeException{
    public BadParamsException(){
        super();
    }
}
