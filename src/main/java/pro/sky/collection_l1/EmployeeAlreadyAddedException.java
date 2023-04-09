package pro.sky.collection_l1;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException(){
        super();
    }
}
