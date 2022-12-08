import { useState } from "react";
import { Modal, Button, Table } from "react-bootstrap";
import url from "../../api/url";
import useGet from "../../hooks/useGet"; 
import Add from "./Add"
import Edit from "./Edit";
import Delete from "./Delete";

const ProductList = () => {
    const { data, loading, error } = useGet(url.product + "/all");
    const [show, setShow] = useState(false);
    const [component, setComponent] = useState(null);
    const [heading, setHeading] = useState(null);

    // const [id, setId] = useState(null);
    // const [name, setName] = useState(null);
    // const [description, setDescription] = useState(null);
    // const [imageFile, setImageFile] = useState(null);
    // const [price, setPrice] = useState(null);
    // const [stock, setStock] = useState(null);
    // const [category, setCategory] = useState(null);


    function loadAdd() {
        setComponent(<Add/>);
        setHeading("Add Product");
        setShow(true);
    }

    function loadEdit(id) {
        setComponent(<Edit id={id} />);
        setHeading("Edit Product");
        setShow(true);
    }

    function loadDelete(id) {
        setComponent(<Delete id={id} />);
        setHeading("Delete Product");
        setShow(true);
    }

    
       

    if (loading) return (<h3>Loading...</h3>);
    if (error != null) return (<h3>{error}</h3>);
    return ( 
        <div className="list">
            <div>
                <Button onClick={() => loadAdd()}>Add product</Button>
            </div>
            <br/>

            <Table className="table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Price</th>
                        <th scope="col">Category</th>
                        <th scope="col">Stock</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((product) => (
                        <tr key={product.id}>{/*onClick="something"*/}
                            <th scope="row">{product.id}</th>
                            <td>{product.name}</td>
                            <td>{product.description}</td>
                            <td>{product.price}</td>
                            <td>{product.category.name}</td>
                            <td>{product.stock}</td>
                            <td>
                                <i onClick={() => loadEdit(product.id)} className="bi bi-gear-fill"></i>
                                <i onClick={() => loadDelete(product.id)} className="bi bi-trash-fill"></i>
                            </td>
                        </tr>
                    ))}

                </tbody>
            </Table>

            <Modal scrollable={true} show={show} onHide={() => setShow(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>
                        {heading}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {component}
                </Modal.Body>
                {/* <Modal.Footer>
                    <Button variant="secondary" onClick={handleSubmit}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleSubmit}>
                        Save Changes
                    </Button>
                </Modal.Footer> */}
            </Modal>
        </div>
     );
}
 
export default ProductList;