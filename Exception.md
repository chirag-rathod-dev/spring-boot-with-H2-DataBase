# Handling Exceptions in Spring Boot with @RestControllerAdvice

## Problem Statement
When removing `@RestControllerAdvice`, the global exception handler is no longer in effect. This results in unhandled exceptions being caught by Spring Boot's default error handling mechanism, which returns a `500 Internal Server Error`.

For example, when calling:

```
GET /products/6
```

If the product with `id=6` is not found, and the controller method throws a `ProductNotFoundException`, the response looks like:

```json
{
    "timestamp": "2025-03-06T06:14:26.000+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/products/6"
}
```

## Root Cause
- The `@RestControllerAdvice` annotation was removed, so global exception handling is disabled.
- The `ProductNotFoundException` is thrown using `.orElseThrow(() -> new ProductNotFoundException(...))`, but it is not handled properly.
- Spring Boot’s default error handling mechanism returns a `500` status code for unhandled exceptions.

## Solution 1: Handle Exception in Controller
A simple way to fix this is to catch the exception inside the controller method and return a proper `404 Not Found` response:

```java
@GetMapping("/products/{id}")
public ResponseEntity<?> getProductById(@PathVariable Long id) {
    try {
        Product product = productService.getProductById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return ResponseEntity.ok(product);
    } catch (ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
```

## Solution 2: Use @RestControllerAdvice for Global Exception Handling
Instead of handling exceptions in each controller, a better approach is to use `@RestControllerAdvice` to centralize exception handling:

```java
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
```

### Benefits of Using @RestControllerAdvice
- **Centralized Exception Handling**: Keeps controllers clean and focused.
- **Reusability**: The same exception handler works across multiple controllers.
- **Better Maintenance**: Changes to exception handling logic can be done in one place.

## Summary
1. Removing `@RestControllerAdvice` results in Spring Boot returning a `500 Internal Server Error` for unhandled exceptions.
2. To properly return a `404 Not Found` response, handle the exception in the controller or use a global exception handler with `@RestControllerAdvice`.
3. Using `@RestControllerAdvice` is the recommended approach for cleaner and more maintainable code.

By implementing one of these solutions, you can ensure proper error handling in your Spring Boot application.

