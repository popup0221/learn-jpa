package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException {
  
  // 메세지 또는 예외(Throwable)을 넣어야 exception trace를 볼 수 있음으로 RuntimeException의 메소드를 override한다.

  public NotEnoughStockException () {
    super();
  }

  public NotEnoughStockException(String message) {
    super(message);
  }

  public NotEnoughStockException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotEnoughStockException(Throwable cause) {
    super(cause);
  }

}
