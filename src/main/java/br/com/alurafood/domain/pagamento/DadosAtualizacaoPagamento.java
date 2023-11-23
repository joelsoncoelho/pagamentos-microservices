package br.com.alurafood.domain.pagamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosAtualizacaoPagamento(
        @NotNull
        Long id,
        @NotNull(message = "O valor não pode ser nulo")
        @Positive
        BigDecimal valor,

        @NotBlank(message = "O campo nome não pode estar em branco")
        @Size(max = 100)
        String nome,

        @NotBlank(message = "O campo numero não pode estar em branco")
        @Size(max = 19)
        String numero,

        @NotNull
        @Future //garantir uma data futura
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime expiracao,

        @NotBlank(message = "O campo código não pode estar em branco")
        @Size(min = 3, max = 3)
        String codigo,

        @NotNull(message = "O status não pode ser nulo")
        Status status
) {
}
