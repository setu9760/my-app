package spring.desai.common.model.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import spring.desai.common.annotations.NotEmptyAndMinMaxSizeID;

public class PaymentDTO implements DTO {

	@NotEmptyAndMinMaxSizeID
	private String id;
	private Double amount;
	@NotEmpty(message = "payment type must be present")
	private String paymentType;
	@NotEmpty(message = "payment must have student id")
	private String studentId;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = JsonFormat.DEFAULT_TIMEZONE)
	private Date date;
	private String comments;

	public PaymentDTO() {
	}

	public PaymentDTO(String id, Double amount, String paymentType, String studentId, Date date, String comments) {
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
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
