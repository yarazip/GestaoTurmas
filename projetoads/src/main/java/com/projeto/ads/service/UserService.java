package com.projeto.ads.service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.ads.model.Role;
import com.projeto.ads.model.Usuario;
import com.projeto.ads.repository.RoleRepository;
import com.projeto.ads.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	

	public Usuario buildData(String data, Usuario usuario) throws ParseException {
		// 2025-04-11
		String aux[] = data.split("-");
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date dataNew = formato.parse(aux[2] + "-" + aux[1] + "-" + aux[0]);
		usuario.setDataNascimento(dataNew);
		return usuario;
	}//fim metodo

	public String validarEmail(Usuario usuario) {
		Usuario aux = userRepository.findByEmail(usuario.getEmail());
		if (aux != null) {
			return "Email já cadastrado!";
		} else {
			return null;
		}
	}//fim

	public String validarSenha(Usuario usuario, String repetirSenha) {
		if (!usuario.getPassword().equals(repetirSenha)) {
			return "Senhas não coincidem!";
		}
		if (usuario.getPassword().length() < 8) {
			return "A senha precisa ter no mínimo 8 caracteres!";
		}
		return null;
	}// fim validarSenha

	public Usuario encriptarSenha(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuario;
	}//fim metodo

	public Usuario defineRole(Usuario user, String role) {
		Role aux= roleRepository.findByNome(role);
		user.setRole(aux);
		return user;
	}
	
	public String atualizarPassword(Usuario usuarioForm, String password, String confirmPass) {
		String saida="";
		Usuario aux= userRepository.findById(usuarioForm.getId()).get();
		if(password.length() < 8) {
			saida="A senha precisa ter no mínimo 8 caracteres!";
		}
		if(!password.equals(confirmPass)) {
			saida+="As senha coincidem!";
		}
		if(!aux.getToken().equals(usuarioForm.getToken())) {
			saida+="Token não confere!";
		}
		if(saida.equals("")) {
			aux.setPassword(passwordEncoder.encode(usuarioForm.getPassword()));
			aux.setToken("");
			userRepository.save(aux);
			return null;
		}
		return saida;
	}
}
