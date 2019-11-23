package MicroTeam.msclienteneg.Entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "cliente")
public class ClienteEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @NotNull
    private String rut;


    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;
    private String tipo_licencia;
    private String fecha_vencimiento;
    private String fecha_emision;

}
