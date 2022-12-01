import { useParams } from "react-router-dom";
import url from "../api/url";
import useFetch from "../hooks/useFetch";

const ProductDetails = () => {
    const { id } = useParams();
    const { data: product, loading, error } = useFetch(url.product + "/" + id)

    console.log(product);
    if (loading) return (<h3>Loading...</h3>);
    if (error != null) return (<h3>{error}</h3>);
    return (
        <div className="product-details">
            <h2>{product.name}</h2>
            <p>{product.description}</p>
        </div>
    );
}
 
export default ProductDetails;