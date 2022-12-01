import { Link } from "react-router-dom";
import url from "../api/url";
import useFetch from "../hooks/useFetch";

const Sidebar = () => {
    const { data, loading, error } = useFetch(url.category + "/all")

    if (loading) return (<h3>Loading...</h3>);
    if (error != null) return (<h3>{error}</h3>);
    return (
        <div>
            <Link to={`/`}>
                <h5 className="">All Products</h5><br/>
            </Link>
            {data.map((category) => (
                <div className="" key={category.id}>
                    <Link to={`/category/${category.id}`}>
                        <h5 className="">{category.name}</h5><br/>
                    </Link>
                </div>
            ))}
        </div>
    );
}
 
export default Sidebar;