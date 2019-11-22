package RentaCar.Arriendo.msarriendoorq.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@Document(collection = "arriendo")

public class ArriendoEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @NotNull
    private String idArriendo;


    private String fechaInicio;
    private String fechaFin;
    private String patenteAuto;
    private String rutCliente;
    private String rutColaborador;
    private int valorArriendo;
    private int totalDias;
}
