package aplicativo.backend.prueba.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "rolOpciones")
public class RolOpciones implements Serializable {
	
	

	private static final long serialVersionUID = 6214472828178594253L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idOpcion;

	    @Column(name = "NombreOpcion" , length = 50)
	    private String nombreOpcion;

	  //  @ManyToOne(fetch = FetchType.LAZY)
	   // EAGER
	    /*
		@ManyToMany(fetch = FetchType.LAZY)
		 @JsonIgnore
		@JoinTable(name = "rol_rolOpciones", 
		joinColumns = @JoinColumn(name = "RolOpciones_idOpcion", referencedColumnName = "idOpcion"), 
		inverseJoinColumns = @JoinColumn(name = "Rol_idRol",referencedColumnName = "idRol"))
	    private List<Rol> roles;
		*/
	    @JsonIgnore
		 @ManyToMany(mappedBy = "rolOpciones")
		    private List<Rol> roles;

		public Integer getIdOpcion() {
			return idOpcion;
		}

		public void setIdOpcion(Integer idOpcion) {
			this.idOpcion = idOpcion;
		}

		public String getNombreOpcion() {
			return nombreOpcion;
		}

		public void setNombreOpcion(String nombreOpcion) {
			this.nombreOpcion = nombreOpcion;
		}

		public List<Rol> getRoles() {
			return roles;
		}

		public void setRoles(List<Rol> roles) {
			this.roles = roles;
		}

		@Override
		public String toString() {
			return "RolOpciones [idOpcion=" + idOpcion + ", nombreOpcion=" + nombreOpcion + ", roles=" + roles + "]";
		}
	   
	
		
		

	    
}
