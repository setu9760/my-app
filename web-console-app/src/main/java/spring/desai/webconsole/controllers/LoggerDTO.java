package spring.desai.webconsole.controllers;

public class LoggerDTO implements Comparable<LoggerDTO>{

	private String name;
	private String parent;
	private String level;

	public LoggerDTO(String name, String parent, String level) {
		super();
		this.name = name;
		this.parent = parent;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public int compareTo(LoggerDTO o) {
		return o.getName().compareTo(getName());
	}
}
