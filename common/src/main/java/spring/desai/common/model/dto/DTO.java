package spring.desai.common.model.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({@JsonSubTypes.Type(value = StudentDTO.class, name = "StudentDTO"),
	@JsonSubTypes.Type(value = SubjectDTO.class, name = "SubjectDTO"),
	@JsonSubTypes.Type(value = TutorDTO.class, name = "TutorDTO"),
	@JsonSubTypes.Type(value = PaymentDTO.class, name = "PaymentDTO"),
	@JsonSubTypes.Type(value = ScholarshipDTO.class, name = "ScholarshipDTO"),
	@JsonSubTypes.Type(value = RoleDTO.class, name = "RoleDTO"),
	@JsonSubTypes.Type(value = CostDTO.class, name = "CostDTO")})
public interface DTO {

	String getId();
	
}
