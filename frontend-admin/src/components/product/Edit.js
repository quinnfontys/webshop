import { Form, Button } from 'react-bootstrap';
// import { Typeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import { useEffect, useState } from 'react';
import url from "../../api/url";
import axios from '../../api/axios';
import useGet from '../../hooks/useGet'

const EditProduct = (props) => {
    const data = props;
    // console.log(data);
    const { data : categories } = useGet(url.category + "/all");

    // const [multiSelections, setMultiSelections] = useState([]);
    // console.log(product.category);

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [imageFile, setImageFile] = useState('');
    const [price, setPrice] = useState('');
    const [categoryId, setCategoryId] = useState('');
    const [stock, setStock] = useState('');


    useEffect(() => {
        setName(data.product.name);
        setDescription(data.product.description);
        setImageFile(data.product.imageFile);
        setPrice(data.product.price);
        setCategoryId(data.product.category.id);
        setStock(data.product.stock);
        // console.log(data.product);
    }, [data.product, data.product.description, data.product.imageFile, data.product.price, data.product.categoryId, data.product.stock])

    const handleSubmit = async (e) => {
        try {
            e.preventDefault();
            const response = await axios.put(url.product, { id : data.product.id, name, description, imageFile, price, stock, "category" : {"id" : categoryId } });
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
            <Form.Group className="mb-3" id="formName">
                <Form.Label>Name</Form.Label>
                <Form.Control required type="text" placeholder="Product name" value={name} onChange={(e) => setName(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" id="formDescription">
                <Form.Label>Description</Form.Label>
                <Form.Control required as="textarea" placeholder="Product description" rows={3} value={description} onChange={(e) => setDescription(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" id="formImage">
                <Form.Label>Image</Form.Label>
                <Form.Control required type="text" placeholder="Product image file name" value={imageFile} onChange={(e) => setImageFile(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" id="formPrice">
                <Form.Label>Price</Form.Label>
                <Form.Control required type="number" placeholder="Product price" min="0" step="0.01" defaultValue={price} onChange={(e) => setPrice(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" id="formCategory">
                <Form.Label>Category</Form.Label>
                {/* <Typeahead
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
                /> */}
                    <Form.Select defaultValue={categoryId} onChange={(e) => setCategoryId(e.target.value)}>
                        {categories.map((category) => (
                            <option defaultValue={category.id} key={category.id}>{category.name}</option>
                        ))}
                    </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" id="formStock">
                <Form.Label>Stock</Form.Label>
                <Form.Control required type="number" placeholder="Product stock" min="0" step="1" defaultValue={stock} onChange={(e) => setStock(e.target.value)}/>
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