import useFetch from "../hooks/useFetch";
import url from "../api/url";
import { Link } from "react-router-dom";


const AllProducts = () => {
    const { data, loading, error } = useFetch(url.product + "/all");


    if (loading) return (<h3>Loading...</h3>);
    if (error != null) return (<h3>{error}</h3>);
    return (
        <div className="products">
            {data.map((product) => (
                <div className="product-preview card" key={product.id}>
                <Link to={`/product/${product.id}`}>
                    <div className="card-body">
                        <h5 className="card-title">{product.name}  {product.price}</h5>
                    </div>
                    <img src={require(`../images/${product.imageFile}`)} className="card-img-top" alt="..."/>
                    <div className="card-body">
                        <p className="card-text">{product.description}</p>
                        <button type="button" className="btn btn-primary"><i className="bi bi-cart-plus"></i></button>
                    </div>
                </Link>
                </div>
            ))}
    </div>
    );
}
 
export default AllProducts;