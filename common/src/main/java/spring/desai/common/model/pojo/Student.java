package spring.desai.common.model.pojo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Student extends Person {

	private static final long serialVersionUID = 3674239599895293427L;
	private int age;
	private Collection<Subject> subjects;
	private Collection<Payment> payments;
	private Collection<Scholorship> scholorships;

	public Student() {
//		this("F_NAME_DUMMY", "L_DUMMY_DUMMY", 18, "ADD");
		this("", "", 25);
	}

	public Student(String f_name, String l_name, int age) {
		this(f_name, l_name, age, "");
	}

	public Student(String f_name, String l_name, int age, String address) {
		super(f_name, l_name, address);
		this.age = age;
		subjects = new HashSet<Subject>();
		payments = new HashSet<Payment>();
		scholorships = new HashSet<Scholorship>();
//		generateGuid();
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

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public void addPayments(Payment payment) {
		this.payments.add(payment);
	}

	public Collection<Scholorship> getScholorships() {
		return scholorships;
	}

	public void setScholorships(Set<Scholorship> scholorships) {
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

//	@Override
	public void generateGuid() {
		if (getId() == null || getId().isEmpty()) {
			setId(f_name.replace("_", "").substring(0, 3) + l_name.replace("_", "").substring(0, 3));
		}
	}
}
