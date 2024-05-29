package eurofarma.com.br.eurofarmachat.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record QuestionDTO(
        @NotEmpty(message = "Ops! Parece que você esqueceu de fazer uma pergunta. Vamos tentar novamente?")
        @Size(min = 3, message = "Hmm, sua pergunta parece um pouco curta. Você poderia fornecer mais detalhes?")
        @Size(max = 400,message = "Uau, isso é bastante texto! Vamos tentar resumir um pouco?")
        String question
) { }
