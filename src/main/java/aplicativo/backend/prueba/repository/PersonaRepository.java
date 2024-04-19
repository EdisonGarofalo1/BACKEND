package aplicativo.backend.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicativo.backend.prueba.model.entities.Persona;

public interface PersonaRepository extends  JpaRepository<Persona, Integer >{

}
