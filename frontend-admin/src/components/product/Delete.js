import { Form, Button } from 'react-bootstrap';
import url from "../../api/url";
import useGet from "../../hooks/useGet";
import useDelete from "../../hooks/useDelete"

const DeleteProduct = (id) => {
    const { data : product } = useGet(url.product, id);

    const HandleSubmit = (e) => {
        const response = useDelete(e, url.product, id);

        if (response?.status === 201) {
            console.log('success');
          }
    }

    return ( 
        <Form onSubmit={HandleSubmit}>
            <Form.Group className="mb-3">
                <Form.Label>Are you ceratin you want to delete {product.name}, #{product.id}?</Form.Label>
            </Form.Group>
            <Form.Group>
                <Button variant="danger" type="submit">
                    Delete
                </Button>
            </Form.Group>
        </Form>
     );
}
 
export default DeleteProduct;