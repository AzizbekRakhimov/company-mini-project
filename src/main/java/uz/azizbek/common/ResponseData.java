package uz.azizbek.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseData<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("timeStamp")
    private Long timeStamp;

    public ResponseData(T data) {
        this.data = data;
        this.errorMessage = "";
        this.timeStamp = System.currentTimeMillis();
    }

    public ResponseData(String successMessage) {
        this.errorMessage = "";
        this.data = (T) successMessage;
        this.timeStamp = System.currentTimeMillis();
    }

    public ResponseData(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.timeStamp = System.currentTimeMillis();
    }

    public ResponseData() {
        this.errorMessage = "";
        this.timeStamp = System.currentTimeMillis();
    }

    public static <T> ResponseEntity<ResponseData<T>> response(T data) {
        return ResponseEntity.ok(new ResponseData<>(data));
    }

    public static <T> ResponseEntity<ResponseData<T>> response(ResponseData<T> responseData, HttpStatus status) {
        return new ResponseEntity<>(responseData, status);
    }

    public static <T> ResponseEntity<ResponseData<T>> response(T data, HttpStatus status) {
        return new ResponseEntity<>(new ResponseData<>(data), status);
    }

    public static <T> ResponseEntity<ResponseData<T>> response(String errorMessage, HttpStatus status) {
        return new ResponseEntity<>(new ResponseData<T>(null, errorMessage), status);
    }

    public static <T> ResponseEntity<ResponseData<T>> response(String errorMessage) {
        return new ResponseEntity<>(new ResponseData<>(null, errorMessage), HttpStatus.BAD_REQUEST);
    }
}
