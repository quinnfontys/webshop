import { Form, Button } from "react-bootstrap";
import { Typeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import { useState } from 'react';
import url from "../../api/url";
import useGet from "../../hooks/useGet"; 
import usePost from "../../hooks/usePost"


const AddProduct = () => {
    const { data : categories } = useGet(url.category + "/all");
    const [multiSelections, setMultiSelections] = useState(categories);
    
    const [name, setName] = useState(null);
    const [description, setDescription] = useState(null);
    const [imageFile, setImageFile] = useState(null);
    const [price, setPrice] = useState(null);
    const [stock, setStock] = useState(null);
    

    const HandleSubmit = (e) => {
        console.log(multiSelections);
        const data = { name, description, imageFile, price, stock, "category" : {"id" : multiSelections[0].id} };
        console.log(data);
        const response = usePost(e, url.product, data);

        if (response?.status === 201) {
            console.log('success');
          }
    }


    return ( 
        <Form onSubmit={HandleSubmit}>
            <Form.Group className="mb-3">
                <Form.Label>Name</Form.Label>
                <Form.Control required type="text" placeholder="Product name" onChange={(e) => setName(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Description</Form.Label>
                <Form.Control required as="textarea" placeholder="Product description" rows={3} onChange={(e) => setDescription(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Image</Form.Label>
                <Form.Control required type="text" placeholder="Product image file name" onChange={(e) => setImageFile(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Price</Form.Label>
                <Form.Control required type="number" placeholder="Product price" min="0" step="0.01" onChange={(e) => setPrice(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Category</Form.Label>
                <Typeahead
                    id="basic-typeahead-multiple"
                    labelKey="name"
                    onChange={setMultiSelections}
                    options={categories}
                    placeholder="Product category"
                    selected={multiSelections}
                />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Stock</Form.Label>
                <Form.Control required type="number" placeholder="Product stock" min="0" step="1" onChange={(e) => setStock(e.target.value)}/>
            </Form.Group>
            <Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form.Group>
        </Form>
    );
}
 
export default AddProduct;