import { useState } from 'react';
import { useEffect } from 'react';
import "../css/product-display.css";

const Home = () => {
    const [products, setProducts] = useState([
    ]);

    const clickMe = () => {
        console.log("button clicked");
    }

    useEffect(() => {
        fetch("http://localhost:8090/api/product")
            .then(res => {
                return res.json();
            })
            .then((data) => {
                console.log(data);
                setProducts(data);
            })
    }, []);
    
    return (
        <div className="home">
            {products.map((product) => (
                <div className="product-preview card" key={product.id}>
                    <div className="card-body">
                        <h5 className="card-title">{product.name}</h5>
                    </div>
                    <img src={require("../images/apple.png")} className="card-img-top" alt="..."/>
                    <div className="card-body">
                        <p className="card-text">{product.description}</p>
                    </div>
                </div>
            ))}

            <button onClick={clickMe}>Click me</button>
        </div>
    );
}
 
export default Home;