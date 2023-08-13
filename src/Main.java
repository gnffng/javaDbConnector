import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DBConnector dbConnector = new DBConnector("codingtest.brique.kr", "employees","codingtest", "12brique!@");

        //query
        String query = "";

        //select
        query += "select e.emp_no, e.first_name, e.last_name, e.gender,e.hire_date";
        query += ", d.dept_name, t.title, max(s.salary) as max_salary";

        //from
        query += " from employees e";

        //join
        query += " join dept_emp de on e.emp_no = de.emp_no";
        query += " join titles t on e.emp_no = t.emp_no";
        query += " join salaries s on e.emp_no = s.emp_no";
        query += " join departments d on de.dept_no = d.dept_no";

        // where
        query += " where e.hire_date >= '2000-01-01'";

        //group by
        query += " group by e.emp_no, de.dept_no";

        System.out.printf("%10s %13s %13s %8s %13s %20s %20s %20s\n",
                "emp_no", "first_name", "last_name", "gender", "hire_date", "dept_name", "title", "max_salary");

        ResultSet rs = dbConnector.selectQuery(query);
        while(rs.next()){
            int emp_no = rs.getInt("emp_no");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            Date hire_date = rs.getDate("hire_date");
            String gender = rs.getString("gender");
            String dept_name = rs.getString("dept_name");
            String title = rs.getString("title");
            int max_salary = rs.getInt("max_salary");

            System.out.printf("%10d %13s %13s %8s %13s %20s %20s %20d\n",
                    emp_no, first_name, last_name, gender, hire_date, dept_name, title, max_salary);
        }
    }
}
