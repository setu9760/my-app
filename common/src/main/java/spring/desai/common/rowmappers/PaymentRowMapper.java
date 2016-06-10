package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Payment;
import static spring.desai.common.utils.DataBaseConstants.*;

public class PaymentRowMapper implements RowMapper<Payment>{

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payment payment = new Payment(rs.getString(ID), rs.getDouble(AMOUNT), PaymentType.valueOf(rs.getString(TYPE)), 
				rs.getString(STUD_ID), rs.getString(ADDITIONAL_COMMENTS));
		payment.setPaymentDateTime(new DateTime(Timestamp.valueOf(rs.getString(DATETIME))));
		return payment;
	}

}
