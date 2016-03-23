package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Payment;

public class PaymentRowMapper implements RowMapper<Payment>{

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payment payment = new Payment(rs.getString(1), rs.getDouble(2), PaymentType.valueOf(rs.getString(3)));
		payment.setStud_id(rs.getString(4));
		payment.setPaymentDateTime(new DateTime(Timestamp.valueOf(rs.getString(5))));
		return payment;
	}

}
