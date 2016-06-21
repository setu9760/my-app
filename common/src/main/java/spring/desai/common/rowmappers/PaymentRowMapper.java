package spring.desai.common.rowmappers;

import static spring.desai.common.utils.DataBaseConstants.ADDITIONAL_COMMENTS;
import static spring.desai.common.utils.DataBaseConstants.AMOUNT;
import static spring.desai.common.utils.DataBaseConstants.DATETIME;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.TYPE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Payment;

@Component("paymentRowMapper")
public class PaymentRowMapper implements RowMapper<Payment>{

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payment payment = new Payment(rs.getString(ID), rs.getDouble(AMOUNT), PaymentType.valueOf(rs.getString(TYPE)), 
				rs.getString(STUD_ID), rs.getString(ADDITIONAL_COMMENTS));
		payment.setPaymentDateTime(new DateTime(Timestamp.valueOf(rs.getString(DATETIME))));
		return payment;
	}

}
