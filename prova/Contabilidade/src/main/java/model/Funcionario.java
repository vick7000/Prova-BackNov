package model;

public class Funcionario {

	public int matricula;
	public String nome;
	public String desligamento;
	public Float salario;
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDesligamento() {
		return desligamento;
	}
	public void setDesligamento(String desligamento) {
		this.desligamento = desligamento;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
	
	/*
	public float calcularIRRF {
		if(salario < 1980.90) {
			float irrf = 0;
		}
		if(salario > 1980.90 && salario < 2940.85) {
			float irrf = this.salario
		}
		*
	}
	*/
}
