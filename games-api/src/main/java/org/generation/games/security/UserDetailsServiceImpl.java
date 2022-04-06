package org.generation.games.security;

import java.util.Optional;

import org.generation.games.model.Usuario;
import org.generation.games.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(username);

		usuario.orElseThrow(() -> new UsernameNotFoundException(username + " Este usuario nao foi encontrado"));

		return usuario.map(UserDetailsImpl::new).get();

	}

}
