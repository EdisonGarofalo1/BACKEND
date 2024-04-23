package aplicativo.backend.prueba.model.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






@Entity
@Table(name = "rol")
public class Rol  implements Serializable {



	private static final long serialVersionUID = 6318088158676989100L;

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(name = "RolName", length = 50)
    private String rolName;
    
/*
	@ManyToMany(fetch = FetchType.EAGER)
	
	@JoinTable(name = "rol_rolOpciones", 
	joinColumns = @JoinColumn(name = "Rol_idRol",referencedColumnName = "idRol"), 
	inverseJoinColumns = @JoinColumn(name = "RolOpciones_idOpcion", referencedColumnName = "idOpcion"))
	 @JsonIgnoreProperties({"rol"})
	private List<RolOpciones> rolOpciones;
	*/
	  @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "rol_rolOpciones", 
	        joinColumns = @JoinColumn(name = "Rol_idRol"), 
	        inverseJoinColumns = @JoinColumn(name = "RolOpciones_idOpcion")
	    )
	  @JsonIgnoreProperties({"rol"})
	    private List<RolOpciones> rolOpciones;
	
	
	 @JsonIgnore
	   @ManyToMany(mappedBy = "roles")
     private List<Usuario> usuarios;
	
	
	
	public List<RolOpciones> getRolOpciones() {
		return rolOpciones;
	}



	public Integer getIdRol() {
		return idRol;
	}



	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}



	public String getRolName() {
		return rolName;
	}



	public void setRolName(String rolName) {
		this.rolName = rolName;
	}



	public List<Usuario> getUsuarios() {
		return usuarios;
	}



	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}



	public void setRolOpciones(List<RolOpciones> rolOpciones) {
		this.rolOpciones = rolOpciones;
	}



	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", rolName=" + rolName + ", rolOpciones=" + rolOpciones + ", usuarios="
				+ usuarios + "]";
	}



	


	

	
	


    
}
