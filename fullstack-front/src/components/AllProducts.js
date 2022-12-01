import useFetch from "../hooks/useFetch";
import url from "../api/url";
import { Link } from "react-router-dom";


const AllProducts = () => {
    const { data, loading, error } = useFetch(url.product);


    if (loading) return (<h3>Loading...</h3>);
    if (error != null) return (<h3>{error}</h3>);
    return (
        <div className="products">
        {data.map((product) => (
            <div className="product-preview card" key={product.id}>
                <Link to={`/product/${product.id}`}>
                    <div className="card-body">
                        <h5 className="card-title">{product.name}</h5>
                    </div>
                    <img src={require(`../images/${product.image_file}`)} className="card-img-top" alt="..."/>
                    <div className="card-body">
                        <p className="card-text">{product.description}</p>
                    </div>
                </Link>
            </div>
        ))}
    </div>
    );
}
 
export default AllProducts;