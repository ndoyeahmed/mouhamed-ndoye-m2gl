package msa.mouhamedndoyem2gl.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * classe pour personnaliser une exception
 *
 * @author Mouhamed NDOYE M2GL
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitGratuitException extends RuntimeException {
    public ProduitGratuitException(String s) {
        super(s);
    }
}
