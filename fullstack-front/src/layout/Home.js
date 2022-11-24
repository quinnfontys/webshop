import { useState } from 'react';
import { useEffect } from 'react';
import "../css/product-display.css";

const Home = () => {
    const [products, setProducts] = useState([
        {name: "gala", description: "beautiful red and yellow apple", id: 1},
        {name: "golden delicious", description: "delicious apple", id: 2},
        {name: "granny smith", description: "green apple", id: 3},
        {name: "fuji", description: "another apple", id: 4},
        {name: "fuji", description: "another apple", id: 5},
        {name: "fuji", description: "another apple", id: 6},
        {name: "fuji", description: "another appleasdfasdf", id: 7},
        {name: "fuji", description: "another apple", id: 8},
        {name: "fuji", description: "another apple", id: 9},
        {name: "fuji", description: "another appleasdfasdfasdf", id: 10},
        {name: "fuji", description: "another apple", id: 11},
        {name: "fuji abracadabra", description: "another apple", id: 12},
        {name: "fuji", description: "another apple", id: 13},
        {name: "fuji", description: "another appleasdfasdfasd", id: 14},
        {name: "fuji", description: "another apple", id: 15},
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