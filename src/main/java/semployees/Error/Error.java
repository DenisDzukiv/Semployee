package semployees.Error;

/**
 * Created by Денис on 04.06.2017.
 */
@SuppressWarnings("serial")
public class Error extends Exception {
    public Error(){}
    public Error(String message){
        super(message);
    }
}
