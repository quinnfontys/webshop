import useFetch from "../hooks/useFetch";
import url from "../api/url";
import { Link } from "react-router-dom";


const Cart = () => {
    const id = 2;

    const { data, loading, error } = useFetch(url.cart + "/" + id);

    if (loading) return (<h3>Loading...</h3>);
    if (error != null) return (<h3>{error}</h3>);
    return (
        <div className="cartProducts">
            {data.cartProducts.map((product) => (
                <div className="product-preview card" key={product.product.id}>
                    <Link to={`/product/${product.product.id}`}>
                        <img src={require("../images/apple.png")} className="card-img-top" alt="..."/>
                        <div className="card-body">
                            <h5 className="card-title">{product.product.name}</h5>
                        </div>
                        <div className="card-body">
                            <p className="card-text">{product.product.description}</p>
                        </div>
                        <div className="product-quantity">
                            
                        </div>
                    </Link>
                </div>
            ))}
        </div>
    );
}
 
export default Cart;