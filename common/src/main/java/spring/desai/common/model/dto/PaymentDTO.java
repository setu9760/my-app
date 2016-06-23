package spring.desai.common.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentDTO {

	String id;
	double amount;
	String paymentType;
	String studentId;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "CET")
	Date date;
	String comments;
	
	public PaymentDTO(String id, double amount, String paymentType, String studentId, Date date, String comments) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentType = paymentType;
		this.studentId = studentId;
		this.date = date;
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
