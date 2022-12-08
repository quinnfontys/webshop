import { Form, Button } from 'react-bootstrap';
import { Typeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import { useEffect, useState } from 'react';
import url from "../../api/url";
import useGet from "../../hooks/useGet";
import usePut from "../../hooks/usePut";


const EditProduct = (id) => {
    const { data : product } = useGet(url.product, id);
    const { data : categories } = useGet(url.category + "/all");
    const [multiSelections, setMultiSelections] = useState([]);
    // console.log(product.category);

    const [name, setName] = useState(null);
    const [description, setDescription] = useState(null);
    const [imageFile, setImageFile] = useState(null);
    const [price, setPrice] = useState(null);
    const [category, setCategory] = useState("something");
    const [stock, setStock] = useState(null);


    useEffect(() => {
        setName(product.name);
        setDescription(product.description);
        setImageFile(product.imageFile);
        setPrice(product.price);
        // console.log(product.price);
        // console.log(product.category.name);
        // setCategory(product.category.name);
        setStock(product.stock);
    }, [product, product.description, product.imageFile, product.price, product.category, product.stock])

    const HandleSubmit = (e) => {
        const data = { id : product.id, name, description, imageFile, price, stock, "category" : {"id" : multiSelections[0].id} };
        console.log(data);
        const response = usePut(e, url.product, data);

        if (response?.status === 201) {
            console.log('success');
          }
    }


    return ( 
        <Form onSubmit={HandleSubmit}>
            <Form.Group className="mb-3">
                <Form.Label>Name</Form.Label>
                <Form.Control required type="text" placeholder="Product name" value={name} onChange={(e) => setName(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Description</Form.Label>
                <Form.Control required as="textarea" placeholder="Product description" rows={3} value={description} onChange={(e) => setDescription(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Image</Form.Label>
                <Form.Control required type="text" placeholder="Product image file name" value={imageFile} onChange={(e) => setImageFile(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Price</Form.Label>
                <Form.Control required type="number" placeholder="Product price" min="0" step="0.01" value={price} onChange={(e) => setPrice(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Category</Form.Label>
                <Typeahead
                    id="basic-typeahead-multiple"
                    // required={true}
                    labelKey="name"
                    onChange={setMultiSelections}
                    options={categories}
                    placeholder="Product category"
                    emptyLabel="No categories found."
                    // selected={multiSelections}
                    
                    // defaultInputValue={category}
                    value={category}
                    // value={product.category}
                />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Stock</Form.Label>
                <Form.Control required type="number" placeholder="Product stock" min="0" step="1" value={stock} onChange={(e) => setStock(e.target.value)}/>
            </Form.Group>
            <Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form.Group>
        </Form>
    );
}
 
export default EditProduct;