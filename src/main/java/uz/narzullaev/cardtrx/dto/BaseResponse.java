package uz.narzullaev.cardtrx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
  private Integer code;
  private String message;
  private T data;


  public static  <T> BaseResponse<T> successResponse(T data){
    BaseResponse<T> res = new BaseResponse<>();
    res.setCode(0);
    res.setData(data);
    res.setMessage("Success");
    return res;
  }
}
