package pro.sky.collection_l1.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException(){
        super();
    }
}
