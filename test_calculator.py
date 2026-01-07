"""Comprehensive unit tests for the calculator module."""

import unittest
from calculator import calc, process_data


class TestCalc(unittest.TestCase):
    """Test cases for the calc function."""

    def test_addition(self):
        """Test addition operation."""
        self.assertEqual(calc(10, 5, '+'), 15)
        self.assertEqual(calc(-5, 3, '+'), -2)
        self.assertEqual(calc(0, 0, '+'), 0)
        self.assertEqual(calc(1.5, 2.5, '+'), 4.0)

    def test_subtraction(self):
        """Test subtraction operation."""
        self.assertEqual(calc(10, 5, '-'), 5)
        self.assertEqual(calc(-5, 3, '-'), -8)
        self.assertEqual(calc(0, 5, '-'), -5)
        self.assertEqual(calc(5.5, 2.5, '-'), 3.0)

    def test_multiplication(self):
        """Test multiplication operation."""
        self.assertEqual(calc(10, 5, '*'), 50)
        self.assertEqual(calc(-5, 3, '*'), -15)
        self.assertEqual(calc(0, 5, '*'), 0)
        self.assertEqual(calc(2.5, 4, '*'), 10.0)

    def test_division(self):
        """Test division operation."""
        self.assertEqual(calc(10, 5, '/'), 2)
        self.assertEqual(calc(-10, 2, '/'), -5)
        self.assertEqual(calc(7, 2, '/'), 3.5)
        self.assertEqual(calc(0, 5, '/'), 0)

    def test_division_by_zero(self):
        """Test that division by zero raises ZeroDivisionError."""
        with self.assertRaises(ZeroDivisionError):
            calc(10, 0, '/')

    def test_invalid_operation(self):
        """Test that invalid operations return None."""
        self.assertIsNone(calc(10, 5, '%'))
        self.assertIsNone(calc(10, 5, '^'))
        self.assertIsNone(calc(10, 5, ''))
        self.assertIsNone(calc(10, 5, 'add'))

    def test_float_precision(self):
        """Test operations with floating point numbers."""
        self.assertAlmostEqual(calc(0.1, 0.2, '+'), 0.3, places=7)
        self.assertAlmostEqual(calc(10.5, 2.5, '/'), 4.2, places=7)


class TestProcessData(unittest.TestCase):
    """Test cases for the process_data function."""

    def test_all_positive_numbers(self):
        """Test with all positive numbers."""
        self.assertEqual(process_data([1, 2, 3, 4, 5]), [2, 4, 6, 8, 10])

    def test_mixed_positive_and_negative(self):
        """Test with mix of positive and negative numbers."""
        self.assertEqual(process_data([1, -2, 3, -4, 5]), [2, 6, 10])

    def test_with_zero(self):
        """Test that zero is not included in result."""
        self.assertEqual(process_data([0, 1, 2, 0, 3]), [2, 4, 6])

    def test_all_negative_numbers(self):
        """Test with all negative numbers returns empty list."""
        self.assertEqual(process_data([-1, -2, -3]), [])

    def test_empty_list(self):
        """Test with empty list."""
        self.assertEqual(process_data([]), [])

    def test_single_positive_number(self):
        """Test with single positive number."""
        self.assertEqual(process_data([5]), [10])

    def test_single_negative_number(self):
        """Test with single negative number."""
        self.assertEqual(process_data([-5]), [])

    def test_float_numbers(self):
        """Test with floating point numbers."""
        self.assertEqual(process_data([1.5, -2.5, 3.5]), [3.0, 7.0])

    def test_large_numbers(self):
        """Test with large numbers."""
        self.assertEqual(process_data([1000, 2000, 3000]), [2000, 4000, 6000])


class TestEdgeCases(unittest.TestCase):
    """Test edge cases and integration scenarios."""

    def test_calc_with_large_numbers(self):
        """Test calculator with very large numbers."""
        self.assertEqual(calc(999999, 1, '+'), 1000000)
        self.assertEqual(calc(1000000, 999999, '-'), 1)

    def test_calc_with_very_small_numbers(self):
        """Test calculator with very small floating point numbers."""
        result = calc(0.0001, 0.0002, '+')
        self.assertAlmostEqual(result, 0.0003, places=10)

    def test_process_data_preserves_order(self):
        """Test that process_data maintains order of elements."""
        data = [5, 1, 9, 2, 7]
        result = process_data(data)
        self.assertEqual(result, [10, 2, 18, 4, 14])


if __name__ == '__main__':
    unittest.main()
