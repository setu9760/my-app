package spring.desai.common.model.enums;

public enum PaymentType {
	CASH, SCHOLORSHIP, CHEQUE, BRAC, DD, OTHER,
	// Other types for future
	PAY1, PAY2, PAY3, PAY4, PAY5, PAY6, PAY7,
	// Test only types
	TEST_0, TEST_1, TEST_2,
	// Types for refund. The payment amount is supposed to be negative with this types
	REFUND_CASH, REFUND_BRAC, REFUND_CHEQUE, REFUND_OTHER
}
