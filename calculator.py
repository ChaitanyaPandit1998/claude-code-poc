"""Calculator module with basic arithmetic operations and data processing."""

from typing import Optional, Union


def calc(a: Union[int, float], b: Union[int, float], op: str) -> Optional[Union[int, float]]:
    """
    Perform basic arithmetic operations on two numbers.

    Args:
        a: First operand
        b: Second operand
        op: Operation to perform ('+', '-', '*', '/')

    Returns:
        Result of the operation, or None if operation is invalid

    Raises:
        ZeroDivisionError: If attempting to divide by zero
    """
    operations = {
        '+': lambda x, y: x + y,
        '-': lambda x, y: x - y,
        '*': lambda x, y: x * y,
        '/': lambda x, y: x / y if y != 0 else None
    }

    if op in operations:
        result = operations[op](a, b)
        if result is None:
            raise ZeroDivisionError("Cannot divide by zero")
        return result
    return None


def process_data(data: list[Union[int, float]]) -> list[Union[int, float]]:
    """
    Process a list of numbers by doubling all positive values.

    Args:
        data: List of numbers to process

    Returns:
        List containing doubled positive values from input
    """
    return [value * 2 for value in data if value > 0]


def main() -> None:
    """Main function to demonstrate calculator usage."""
    result = calc(10, 5, '+')
    print(result)


if __name__ == "__main__":
    main()