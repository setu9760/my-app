package spring.desai.common.model;

import static spring.desai.common.utils.DataBaseConstants.AGE;
import static spring.desai.common.utils.DataBaseConstants.STUDENT_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.SUBJECT_STUDENT_LINK_TABLE_NAME;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = STUDENT_TABLE_NAME)
public class Student extends Person {

	private static final long serialVersionUID = 3674239599895293427L;
	
	@Column(name = AGE)
	private int age;
	
	@ManyToMany(fetch =FetchType.EAGER)
	@JoinTable(name = SUBJECT_STUDENT_LINK_TABLE_NAME, 
			joinColumns= @JoinColumn(name = "stud_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "subj_id", referencedColumnName ="id"))
	private Collection<Subject> subjects = new HashSet<Subject>();
	
	@OneToMany(mappedBy = STUD_ID, fetch=FetchType.EAGER)
	private Collection<Payment> payments = new HashSet<Payment>();
	
	@OneToMany(mappedBy = STUD_ID, fetch=FetchType.EAGER)
	private Collection<Scholorship> scholorships = new HashSet<Scholorship>();

	public Student() {
		this(null, null, null, 0, null);
	}
	
	public Student(String f_name, String l_name, int age) {
		this(f_name, l_name, age, null);
	}

	public Student(String f_name, String l_name, int age, String address) {
		this(null, f_name, l_name, age, address);
	}
	
	public Student(String id, String f_name, String l_name, int age, String address) {
		super(id, f_name, l_name, address);
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSubjects(Collection<Subject> subjects) {
		this.subjects = subjects;
	}

	public Collection<Subject> getSubjects() {
		return subjects;
	}

	public void addSubject(Subject subject) {
		subjects.add(subject);
	}

	public Collection<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Collection<Payment> payments) {
		this.payments = payments;
	}

	public Collection<Scholorship> getScholorships() {
		return scholorships;
	}

	public void setScholorships(Collection<Scholorship> scholorships) {
		this.scholorships = scholorships;
	}

	public void addScholorship(Scholorship scholorship) {
		this.scholorships.add(scholorship);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id= ");
		builder.append(id);
		builder.append(", f_name= ");
		builder.append(f_name);
		builder.append(", l_name= ");
		builder.append(l_name);
		builder.append(", address= ");
		builder.append(address);
		builder.append(", age= ");
		builder.append(age);
		builder.append(", subjects= ");
		builder.append(subjects);
		builder.append(", payments= ");
		builder.append(payments);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + age;
		result = prime * result + ((payments == null) ? 0 : payments.hashCode());
		result = prime * result + ((scholorships == null) ? 0 : scholorships.hashCode());
		result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (payments == null) {
			if (other.payments != null)
				return false;
		} else if (!payments.equals(other.payments))
			return false;
		if (scholorships == null) {
			if (other.scholorships != null)
				return false;
		} else if (!scholorships.equals(other.scholorships))
			return false;
		if (subjects == null) {
			if (other.subjects != null)
				return false;
		} else if (!subjects.equals(other.subjects))
			return false;
		return true;
	}
}
