# Project Guidelines

This repository contains educational examples demonstrating programming concepts in Java and Python.

## Project Structure

- **Java files**: Concurrency and threading demonstrations
- **Python files**: Clean code examples with comprehensive testing
- **Compiled files**: `.class` files are gitignored (not committed)

## Code Style Guidelines

### Java
- Use meaningful class and variable names (e.g., `SharedBuffer`, `itemCounter`)
- Include JavaDoc comments for classes and public methods
- Follow standard Java naming conventions:
  - Classes: PascalCase
  - Methods/variables: camelCase
  - Constants: UPPER_SNAKE_CASE
- Use proper synchronization for multi-threaded code
- Handle InterruptedException appropriately in thread code

### Python
- Follow PEP 8 style guidelines
- Use type hints for function parameters and return types
- Include docstrings for all functions and classes (Google style preferred)
- Use descriptive variable names
- Prefer list comprehensions for simple transformations

## Testing Standards

### Python Tests
- Write comprehensive unit tests using `unittest` framework
- Organize tests into logical test classes
- Include edge case testing (empty inputs, zero values, large numbers)
- Test error conditions with `assertRaises`
- Use descriptive test method names starting with `test_`

### Java
- When adding new Java features, consider adding appropriate test cases
- Test concurrent behavior and thread safety

## Git Workflow

### Committing
- Write clear, descriptive commit messages
- Use conventional commit format when applicable
- Ensure code compiles before committing
- Run tests before committing changes

### What to Commit
- Source code files (`.java`, `.py`)
- Test files
- Configuration files
- Documentation

### What NOT to Commit
- Compiled files (`.class` files)
- IDE-specific files
- Temporary files
- Environment-specific configurations

## Documentation

- Keep code self-documenting with clear naming
- Add comments for complex logic or algorithms
- Document thread synchronization mechanisms
- Explain any non-obvious design decisions

## Best Practices

### Concurrency (Java)
- Always use proper synchronization primitives (`synchronized`, `wait()`, `notify()`)
- Avoid busy-waiting; use `wait()` and `notify()` instead
- Handle thread interruption gracefully
- Document thread safety guarantees

### Error Handling
- Raise appropriate exceptions with clear messages
- Handle division by zero and other edge cases
- Validate inputs at function boundaries

### Code Organization
- Keep classes focused on a single responsibility
- Separate concerns (e.g., buffer management, producer logic, consumer logic)
- Group related functionality together
