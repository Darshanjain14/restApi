package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.DBConnect;
import com.google.gson.Gson;
import com.user.User;
import com.response.*;

public class UserDAO {
	public static Gson gson = new Gson();

	public standardResponse addUser(User us) {
		standardResponse sr = new standardResponse();
		try {
			Connection conn = DBConnect.getConnection();
			String query = "insert into user(name,email,mobile,address,pin,about) values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);

			if (us.getPin() == 0) {
				sr = new standardResponse("400", "pin field is empty");
				return sr;
			}
			if (us.getName() == null) {

				sr = new standardResponse("400", "name field is empty");
				return sr;

			}
			if (us.getEmail() == null) {

				sr = new standardResponse("400", "email field is empty");
				return sr;

			}
			if (us.getMobile() == null) {

				sr = new standardResponse("400", "mobile field is empty");
				return sr;

			}
			if (us.getAddress() == null) {

				sr = new standardResponse("400", "address field is empty");
				return sr;

			}
			if (us.getAbout() == null) {

				sr = new standardResponse("400", "about field is empty");
				return sr;

			}

			ps.setString(1, us.getName());
			ps.setString(2, us.getEmail());
			ps.setString(3, us.getMobile());
			ps.setString(4, us.getAddress());
			ps.setInt(5, us.getPin());
			ps.setString(6, us.getAbout());
			int status = ps.executeUpdate();

			if (status == 1) {
				sr = new standardResponse("201", "user added Successfully");
				return sr;
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return sr;

	}

	public standardResponse getUser(int id) 
	{
		standardResponse sr = new standardResponse();
		Connection conn = DBConnect.getConnection();
		String query = "select * from user where id=" + id;
		try 
		{
			Statement stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			User us=null;
			while(rs.next())
			{
				us=new User();
				us.setName(rs.getString("name"));
				us.setEmail(rs.getString("email"));
				us.setMobile(rs.getString("mobile"));
				us.setAddress(rs.getString("address"));
				us.setPin(Integer.parseInt(rs.getString("pin")));
				us.setAbout(rs.getString("about"));
			}
			if(us!=null)
			{
			sr.setStatusResponse("201");
			sr.setData(gson.toJsonTree(us));
			}
			else
			{
				sr.setStatusResponse("401");
				sr.setMessage("user with id "+id+" not exists it the database." );
				sr.setData(null);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			
		}

		return sr;

	}

	public standardResponse getAllUser() {
		standardResponse sr = new standardResponse();
		List<User> list = new ArrayList<>();
		try
		{
			Connection conn = DBConnect.getConnection();
			String sql ="select * from user";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			User us;
			while(rs.next())
			{
				us = new User();
				us.setName(rs.getString("name"));
				us.setEmail(rs.getString("email"));
				us.setMobile(rs.getString("mobile"));
				us.setAddress(rs.getString("address"));
				us.setPin(rs.getInt("pin"));
				us.setAbout(rs.getString("about"));
				list.add(us);
			}
			sr.setStatusResponse("201");
			sr.setData(gson.toJsonTree(list));
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			sr.setStatusResponse("401");
			sr.setMessage("something is wrong.");
		}
		return sr;
	}

	public standardResponse updateUser(int id,User us) {
		standardResponse sr = new standardResponse();
		try
		{
			Connection conn = DBConnect.getConnection();
			String sql = "update user set name=?,email=?,mobile=?,address=?,pin=?,about=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,us.getName());
			ps.setString(2,us.getEmail());
			ps.setString(3, us.getMobile());
			ps.setString(4, us.getAddress());
			ps.setInt(5,us.getPin());
			ps.setString(6, us.getAbout());
			ps.setInt(7, id);
			int status = ps.executeUpdate();
			if(status==1)
			{

				sr.setStatusResponse("201");
				sr.setMessage("user updated Succesfully.");
				
			}
			else
			{
				sr.setStatusResponse("400");
				sr.setMessage("user with id "+id+"does not exists in data base.");
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return sr;
	}

	public standardResponse deleteUser(int id) {
		standardResponse sr = new standardResponse();
		try
		{
			Connection conn = DBConnect.getConnection();
			String sql = "delete from user where id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int status = ps.executeUpdate();
			if(status==1)
			{
				sr.setStatusResponse("201");
				sr.setMessage("user deleted Succesfully.");
			}
			else
			{
				sr.setStatusResponse("400");
				sr.setMessage("user does not exists.");
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return sr;
	}
}
