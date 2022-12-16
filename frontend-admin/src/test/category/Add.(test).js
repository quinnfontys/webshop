import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import Add from '../../components/category/Add';

test("It should allow text to be inputted", () => {
    render(<Add/>);

    const input = screen.getByTestId("formName");
    fireEvent.change(input, {targest: {value: 'Pineapple'}});
    expect(input.value).toBe('Pineapple');
})

