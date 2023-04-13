package in.nareshit.raghu.exception;

public class CouponNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponNotFoundException() {
	}

	public CouponNotFoundException(String message) {
		super(message);
	}
}
