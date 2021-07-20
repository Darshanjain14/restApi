package SimpleRestApi;

import static spark.Spark.*;

import com.DAO.UserDAO;
import com.google.gson.Gson;
import com.response.standardResponse;
import com.user.User;

public class userApi {

	public static Gson gson = new Gson();
	public static UserDAO userDAO = new UserDAO();
	public static void main(String args[]) {

		before((req, res) -> {
			res.type("application/json");
		});

		post("/add", (req, res) -> {
			User user = gson.fromJson(req.body(), User.class);
			standardResponse rr = userDAO.addUser(user);
			if (rr.getStatusResponse().equals("401")) {
				halt(401, "something is wrong!!!");
			}
			return gson.toJson(rr);

		});
		get("/users", (req, res) -> {
			standardResponse rr = userDAO.getAllUser();
			if (rr.getStatusResponse().equals("401")) {
				halt(401, "something is wrong!!!");
			}
			return gson.toJson(rr);
			
			

		});
		get("/user/:id", (req, res) -> {
			standardResponse rr = userDAO.getUser(Integer.parseInt(req.params(":id")));
			if (rr.getStatusResponse().equals("401")) {
				halt(401, "something is wrong!!! Id does not exist in database ");
			}
			return gson.toJson(rr);

		});
		put("/update/:id", (req, res) -> {
			User user = gson.fromJson(req.body(),User.class);
			standardResponse rr = userDAO.updateUser(Integer.parseInt(req.params(":id")),user);
			if(rr.getStatusResponse().equals("401"))
			{
				halt(401, "something is wrong!!! Id does not exist in database ");
			}
			return gson.toJson(rr);
		});
		delete("/delete/:id", (req, res) -> {
			standardResponse rr = userDAO.deleteUser(Integer.parseInt(req.params(":id")));
			if(rr.getStatusResponse().contentEquals("401"))
			{
				halt(401, "something is wrong!!! Id does not exist in database ");
			}
			return gson.toJson(rr);
		});

		get("/stop", (req, res) -> {
			stop();
			return "stopped";
		});
	}

}
