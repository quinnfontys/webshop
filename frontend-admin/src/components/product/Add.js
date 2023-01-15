import { Form, Button } from "react-bootstrap";
// import { Typeahead } from 'react-bootstrap-typeahead';
// import 'react-bootstrap-typeahead/css/Typeahead.css';
import { useState } from 'react';
import url from "../../api/url";
import useGet from "../../hooks/useGet"; 
import axios from "../../api/axios";


const AddProduct = (props) => {
    const { data : categories } = useGet(url.category + "/all");
    // const [multiSelections, setMultiSelections] = useState(categories);
    
    const [name, setName] = useState(null);
    const [description, setDescription] = useState(null);
    const [imageFile, setImageFile] = useState(null);
    const [price, setPrice] = useState(null);
    const [categoryId, setCategoryId] = useState(null);
    const [stock, setStock] = useState(null);
    
    const handleSubmit = async (e) => {
        try {
            e.preventDefault();
            const response = await axios.post(url.product, { name, description, imageFile, price, stock, "category" : {"id" : 1 } });
            if (response?.status === 200) {
                const response = await axios.get(url.product + "/all");
                if (response?.status === 200){
                    props.hide(e)(response.data);

                }
            } 
        } catch (err) {
            console.log(err.message);
        }
    }


    return (
        <Form onSubmit={handleSubmit}>
            {/* <Button variant="secondary" onClick={hide()}>
                HIDE
            </Button> */}
            <Form.Group className="mb-3" id="formName">
                <Form.Label>Name</Form.Label>
                <Form.Control required type="text" placeholder="Product name" onChange={(e) => setName(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" id="formDescription">
                <Form.Label>Description</Form.Label>
                <Form.Control required as="textarea" placeholder="Product description" rows={3} onChange={(e) => setDescription(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" id="formImage">
                <Form.Label>Image</Form.Label>
                <Form.Control required type="text" placeholder="Product image file name" onChange={(e) => setImageFile(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" id="formPrice">
                <Form.Label>Price</Form.Label>
                <Form.Control required type="number" placeholder="Product price" min="0" step="0.01" onChange={(e) => setPrice(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" id="formCategory">
                <Form.Label>Category</Form.Label>
                {/* <Typeahead
                    id="basic-typeahead-multiple"
                    labelKey="name"
                    onChange={setMultiSelections}
                    options={categories}
                    placeholder="Product category"
                    selected={multiSelections}
                /> */}
                    <Form.Select defaultValue={categoryId} onChange={(e) => setCategoryId(e.target.value)}>
                        {categories.map((category) => (
                            <option key={category.id} value={category.id}>{category.name}</option>
                        ))}
                    </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" id="formStock">
                <Form.Label>Stock</Form.Label>
                <Form.Control required type="number" placeholder="Product stock" min="0" step="1" onChange={(e) => setStock(e.target.value)}/>
            </Form.Group>
            <Form.Group id="formSubmit">
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form.Group>
        </Form>
    );
}
 
export default AddProduct;