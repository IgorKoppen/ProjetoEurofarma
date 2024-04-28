package eurofarma.com.br.eurofarmachat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ArchiveTypeExeception extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ArchiveTypeExeception(String message) {
        super(message);
    }
    public ArchiveTypeExeception() {
        super("Archive type not supported");
    }
}
