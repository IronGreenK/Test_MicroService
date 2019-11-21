package MicroTeam.RentaCar.Automovil.entity;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document (collection= "automovil")

public class AutomovilEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    @Id
    @NotNull
    private String id;
    private String marca;
    private String modelo;
    private String patente;
    private int annoFabricacion;
    private int revisionTecnica;
    private String estado;
    private int valorDiario;


}
