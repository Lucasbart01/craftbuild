package br.com.craftbuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import javax.validation.Valid;

import br.com.craftbuild.beans.Usuario;
import br.com.craftbuild.dao.UsuarioDAO;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	//Chamando página de cadastro
		@GetMapping("/cadastro")
		public String cadastro(Usuario user) {
		
			return "index";
		}
		
		
	//Chamando página de login
	@GetMapping("/login")
	public String login() {
	
		return "login";
	}
	
	//Chamando página de update
		@GetMapping("/edit/{id}")
		public String update() {
		
			return "update";
		}
		

	@PostMapping("/login/entry")
	public ResponseEntity<Usuario> getAllByEmailAndSenha(@RequestBody Usuario usuario) {
		System.out.println(usuario);
		System.out.println("Email: " + usuario.getEmail());
		System.out.println("Senha: " + usuario.getSenha());
		Usuario resposta = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
	
		System.out.println(resposta);
		return ResponseEntity.ok(resposta);
	}

	
	//Select
	 @GetMapping("/user")
	    public String showUpdateForm(Model model) {
	        model.addAttribute("users", dao.findAll());
	        return "area";
	    }
	 
	 
	 //Create
	 @PostMapping("/add")
		public ResponseEntity<Usuario> getSave(@RequestBody Usuario usuario) {
			try {
				dao.save(usuario);
				return ResponseEntity.ok(usuario);

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(404).build();
			}
		}
	 
	 
	 //Update
	 
	 @PutMapping("/update/{id}")
		public ResponseEntity<Usuario> getUpdate(@PathVariable Integer id, @RequestBody Usuario user) {
		 return dao.findById(id)
				 .map(usuario -> {
					 usuario.setNome(user.getNome());
					 usuario.setEmail(user.getEmail());
					 usuario.setSenha(user.getSenha());
					 usuario.setMotivo(user.getMotivo());
					 Usuario userUpdate = dao.save(usuario);
					 return ResponseEntity.ok().body(userUpdate);
				 }).orElse(ResponseEntity.notFound().build());
			
		}
	 
	 //Delete
	 @GetMapping("delete/{id}")
	    public String deleteStudent(@PathVariable("id") int id, Model model) {
	        Usuario usuario = dao.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("ID de usuário inválido:" + id));
	        dao.delete(usuario);
	        model.addAttribute("users", dao.findAll());
	        return "area";
	    }
	 
	
}