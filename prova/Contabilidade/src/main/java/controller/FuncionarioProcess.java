package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Funcionario;

public class FuncionarioProcess {
	private Connection con;
	private ResultSet rs;
	
	public FuncionarioProcess() {
		this.con = new ConnectionDB().getConnection();
	}
	
	public boolean create(Funcionario funcionario) {
		String query = "INSERT INTO funcionarios VALUES (DEFAULT,?,?,?)";
		
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,  funcionario.getNome());
			ps.setString(2,  funcionario.getDesligamento());
			ps.setFloat(3,  funcionario.getSalario());
			
			
			if(ps.executeUpdate() > 0) {
				rs = ps.getGeneratedKeys();
				rs.next();
				int novaMatricula = rs.getInt(1);
				funcionario.setMatricula(novaMatricula);
				con.close();
				return true;
			} else {
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public JSONArray readAll() {
	  JSONArray arr = new JSONArray();
	 
	  String query = "SELECT * FROM funcionarios";
	  
	  
	try {
		PreparedStatement ps = con.prepareStatement(query);
		
		 rs = ps.executeQuery();
		
		  while(rs.next()) {
		  		JSONObject obj = new JSONObject();
		  		obj.put("matricula", rs.getInt(1));
		  		obj.put("nome", rs.getString(2));
		  		obj.put("desligamento", rs.getString(3));
		 		obj.put("salario", rs.getFloat(4));
		 
		  	arr.put(obj);
		  }
		  
		  con.close();	 
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (JSONException e) {
		e.printStackTrace();
	}
	  return arr;
	  }

	/*
	public String update(Funcionario funcionario) {
		String query = "UPDATE funcionarios SET matricula = ?, nome= ?, desligamento = ?, salario = ?";
		
	try {
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1,  funcionario.getMatricula());
		ps.setString(2, funcionario.getNome());
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	}
	*/
	
}
