package br.com.craftbuild.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_user")
public class Usuario {
	
	@Column(name="id")
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name ="nome", length=50)
	private String nome;
	@Column(name ="senha", length=30)
	private String senha;
	@Column(name="email", length=70)
	private String email;
	@Column(name="motivo", length=250)
	private String motivo;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
		
}

