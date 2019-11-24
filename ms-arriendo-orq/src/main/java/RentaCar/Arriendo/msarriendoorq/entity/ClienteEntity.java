package RentaCar.Arriendo.msarriendoorq.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
//@Document(collection = "cliente")
public class ClienteEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @NotNull
    private String Id;

    private String rut;
    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;
    private String tipo_licencia;
    private LocalDate fecha_vencimiento;
    private LocalDate fecha_emision;

}
