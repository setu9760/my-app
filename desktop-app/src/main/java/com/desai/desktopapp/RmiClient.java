package com.desai.desktopapp;

public class RmiClient {
//
//	static StudentDao studentDao;
//	static TutorDao tutorDao;
//	static SubjectDao subjectDao;

	public static void main(String[] args) throws InterruptedException {
//		ApplicationContext context = new AnnotationConfigApplicationContext(Rmi_Client_Config.class);
//
//		studentDao = (StudentDao) context.getBean("studentService");
//
//		tutorDao = (TutorDao) context.getBean("tutorService");
//
//		subjectDao = (SubjectDao) context.getBean("subjectService");
//
//		Scanner scanner = new Scanner(System.in);
//
//		while (true) {
//			showMainMenu();
//
//			int input = scanner.nextInt();
//
//			showSubMenu(input);
//
//			int input2 = scanner.nextInt();
//
//			performAction(input, input2);
//		}

		// int JVM = 0;
		//
		// JVM = (int) (System.currentTimeMillis() >>> 6);
		// System.out.println(Integer.toHexString(JVM).toUpperCase());

	}

	private static void showMainMenu() {
		System.out.println("!!!!! Please select on of the following option !!!!!");
		System.out.println("1: Student");
		System.out.println("2: Teacher");
		System.out.println("3: Subject");
		System.out.println("-1: Exit");
	}

	private static void showSubMenu(int input) {
		String menuItem = null;
		switch (input) {
		case 1:
			menuItem = "Student";
			break;
		case 2:
			menuItem = "Tutor";
			break;
		case 3:
			menuItem = "Subject";
			break;
		case -1:
			System.out.println("Good Bye !!!");
			System.exit(0);
			break;
		default:

			break;
		}

		System.out.println("!!!!! Please select on of the following option !!!!!");
		System.out.println("1: Insert " + menuItem);
		System.out.println("2: Search " + menuItem);
		System.out.println("3: Count all " + menuItem + "s");
		System.out.println("4: View all " + menuItem + "s");
		System.out.println("5: Insert all");
		System.out.println("-1: Previous Menu");

	}

//	private static void performAction(int selection, int action) {
//		BaseDao dao = null;
//		switch (selection) {
//		case 1:
//			dao = studentDao;
//			break;
//		case 2:
//			dao = tutorDao;
//			break;
//		case 3:
//			dao = subjectDao;
//			break;
//		default:
//			break;
//		}
//
//		performAction(dao, action);
//	}
//
//	private static void performAction(BaseDao dao, int action) {
//		switch (action) {
//		case 1:
//
//			break;
//		case 2:
//
//			break;
//		case 3:
//			System.out.println(dao.countAll());
//			break;
//		case 4:
//			System.out.println(dao.getAll());
//			break;
//		case 5:
//			List<Serializable> list = new ArrayList<Serializable>();
//			for (int i = 0; i < 10; i++) {
//				Student s = null;
//				try {
//					s = new Student("student-" + i, "", 21);
//					s.setId(String.valueOf(i));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				list.add(s);
//			}
//			try {
//				Student s = new Student();
//				s.setId(String.valueOf(1));
//				list.add(s);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			dao.insertAll(list);
//			
//			break;
//		default:
//			break;
//		}
//	}
}
