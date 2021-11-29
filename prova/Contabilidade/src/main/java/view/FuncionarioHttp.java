package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.FuncionarioProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Funcionario;


@WebServlet("/funcionario")
public class FuncionarioHttp extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		try {
			JSONObject obj = new JSONObject(body);
			
			int matricula = obj.getInt("matricula");
			String nome = obj.getString("nome");
			String desligamento = obj.getString("desligamento");
			float salario = (float) obj.getDouble("salario");
			
			Funcionario f = new Funcionario();
			f.setMatricula(matricula);
			f.setNome(nome);
			f.setDesligamento(desligamento);
			f.setSalario(salario);
			
			FuncionarioProcess fp = new FuncionarioProcess();
			
			if(fp.create(f) == true) {
				obj.put("matricula", f.getMatricula());
				pw.write(obj.toString());
			} else {
				resp.setStatus(401);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
				
	}

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw =resp.getWriter();
		
		FuncionarioProcess cp = new FuncionarioProcess();
		
		JSONArray arr = cp.readAll();
		
		pw.write(arr.toString());
	}
}
