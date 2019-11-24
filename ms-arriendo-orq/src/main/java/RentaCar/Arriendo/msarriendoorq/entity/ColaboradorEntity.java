package RentaCar.Arriendo.msarriendoorq.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
//@Builder
//@Document(collection = "MicroAutomotora")
public class ColaboradorEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @NotNull
    private String rut;


    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sexo;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIncorporacion;
    private String nivelPermiso;
    private int sueldoBase;
    private float bonoServicio;
    private float sueldoMasBono;

    public ColaboradorEntity() {    }
}


