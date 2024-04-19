package aplicativo.backend.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicativo.backend.prueba.model.entities.Rol;

public interface RolRepository extends  JpaRepository< Rol, Integer> {

}
