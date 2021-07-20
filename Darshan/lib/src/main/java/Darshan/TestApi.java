package Darshan;
import com.darshan.*;
import static spark.Spark.*;
import com.google.gson.Gson;

 public class TestApi
{	
	public static void main(String args[])
	{
		StudentService ss = new StudentService();
		ss.addStudent(new Student("darshan jain",101));
		ss.addStudent(new Student("harshit choukse",102));
		ss.addStudent(new Student("harsh sharma",103));
		
		port(8000);
		
		get("/getStudents",(req,res)->{
			
			res.type("application/json");
			return new Gson().toJson(new Gson().toJsonTree(ss.getStudents()));
		});
		
		
		get("/getStudent/:rollNumber",(req,res)->{
			res.type("application/json");
			Student s = ss.getStudent(Integer.parseInt(req.params(":rollNumber")));
			return new Gson().toJson(s);
		});
		
		post("/addStudent",(req,res)->{
			
			res.type("application/json");
			String name = req.queryParams("name");
			int rollNumber = Integer.parseInt(req.queryParams("rollNumber"));
			Student s = new Student(name,rollNumber);
			ss.addStudent(s);
			return "{\"status\":\"success\"}";
		});
		
		
		
		delete("/deleteStudent/:id",(req,res)->{
			
			res.type("application/json");
			ss.deleteStudent(Integer.parseInt(req.params(":id")));
			return "{\"status\":\"success\"}";
			
		});
		
		
		put("/updateStudent/:id/:name",(req,res)->{
			res.type("application/json");
			Student s = new Student(req.params(":name"),Integer.parseInt(req.params(":id")));
			ss.editStudent(Integer.parseInt(req.params(":id")), s);
			return new Gson().toJson(s);
		});
		
		
		get("/stop",(req,res)->{
				
			stop();
			return "server is stopped";
		});
	
		
		
	}
	
}