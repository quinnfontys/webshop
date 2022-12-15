import { render, fireEvent, screen } from '@testing-library/react';
import Add from '../../components/product/Add';



test("It should allow text to be inputted", () => {
    render(<Add/>);

    const input = screen.getByTestId("formName");
    fireEvent.change(input, {targest: {value: 'PineApple'}});
    expect(input.value).toBe('PineApple');
})


//test block
// test("Adding product form", () => {
//     // render the component on virtual dom
//     // render(<Add />);

    
//     //select elements to interact with
//     // const descriptionInput = screen.getByTestId("formDescription");
//     // const imageInput = screen.getByTestId("formInput");
//     // const priceInput = screen.getByTestId("formPrice");
//     // const categoryInput = screen.getByTestId("formCategory");
//     // const stockInput = screen.getByTestId("formStock");

//     // const submitBtn = screen.getByTestId("increment");
    
//     //interact with those elements
//     // fireEvent.click(submitBtn);
    
//     //assert the expected result
//     expect(counter).toHaveTextContent("1");
// });