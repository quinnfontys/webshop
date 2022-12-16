import { Form } from "react-bootstrap";
import { useState } from 'react';

const AddCategory = ({hide}) => {
    
    const [name, setName] = useState(null);
    const [description, setDescription] = useState(null);
    

    const handleSubmit = (e) => {
        const data = { name, description, imageFile, price, stock, "category" : {"id" : categoryId} };
        console.log(data);
    }


    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3" id="formName">
                <Form.Label>Name</Form.Label>
                <Form.Control required type="text" placeholder="Product name" onChange={(e) => setName(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" id="formDescription">
                <Form.Label>Description</Form.Label>
                <Form.Control required as="textarea" placeholder="Product description" rows={3} onChange={(e) => setDescription(e.target.value)}/>
            </Form.Group>
        </Form>
    );
}
 
export default AddCategory;