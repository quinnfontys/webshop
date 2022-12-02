import CartProducts from "./CartProducts";
import "../css/cart.css";

const Cart = () => {
    return ( 
        <div className="row cart">
            <div className="col-10">
                <CartProducts />
            </div>
            <aside className="col-2">
                <p>checkout</p>
            </aside>
        </div>
     );
}
 
export default Cart;