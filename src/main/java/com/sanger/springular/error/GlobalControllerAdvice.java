package com.sanger.springular.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.sanger.springular.error.exceptions.LoteNotFoundException;
import com.sanger.springular.error.exceptions.NewUserWithDifferentPasswordsException;
import com.sanger.springular.error.exceptions.PasswordNotMismatch;
import com.sanger.springular.error.exceptions.UserNotFoundException;
import com.sanger.springular.error.exceptions.ProductoNotFoundException;
import com.sanger.springular.error.exceptions.SearchProductoNoResultException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ProductoNotFoundException.class, SearchProductoNoResultException.class,
			UserNotFoundException.class, LoteNotFoundException.class })
	public ResponseEntity<ApiError> handleNoEncontrado(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	@ExceptionHandler({ NewUserWithDifferentPasswordsException.class, PasswordNotMismatch.class })
	public ResponseEntity<ApiError> handleBadRequest(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Error de validacion", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}

}
