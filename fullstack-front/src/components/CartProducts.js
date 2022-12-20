import useFetch from "../hooks/useFetch";
import url from "../api/url";
import { Link } from "react-router-dom";
import Cookies from "js-cookie";


const Cart = () => {
    const JWT = Cookies.get('JWT');

    const { data, loading, error } = useFetch(url.cart, {"jwsString" : JWT});

    if (loading) return (<h3>Loading...</h3>);
    if (error != null) return (<h3>{error}</h3>);
    return (
        <div className="cartProducts">
            {data.cartProducts.map((product) => (
                <Link to={`/product/${product.product.id}`} key={product.product.id}>
                    <div className="cart-product-preview card">
                        <div className="cart-product-image">
                            <img src={require(`../images/${product.product.imageFile}`)} className="card-img-top" alt="..."/>
                        </div>
                        <div className="cart-product-title">
                            <h5 className="card-title">{product.product.name}</h5>
                        </div>
                        <div className="cart-product-price">
                            <p className="card-text">{product.product.price}</p>
                        </div>
                        <div className="cart-product-quantity">
                            <p className="card-text">{product.quantity}</p>
                        </div>
                        
                    </div>
                </Link>
            ))}
        </div>
    );
}
 
export default Cart;