package com.github.integrador.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String Username);
}

//import java.util.Optional;
//import org.springframework.data.repository.CrudRepository;
//
//public interface UserRepository extends CrudRepository<Usuario, String> {
//    Optional<User> findByUsername(String username);
//}