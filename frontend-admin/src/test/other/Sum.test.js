import React from 'react';
import { render, fireEvent, screen } from "@testing-library/react";
import Sum from "../../components/other/Sum";

//test block
test("increments counter", () => {
// render the component on virtual dom
    render(<Sum />);
});