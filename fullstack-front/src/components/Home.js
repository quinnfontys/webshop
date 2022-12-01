import { Routes, Route } from "react-router-dom";
import AllProducts from "./AllProducts";
import ProductCategory from "./ProductCategory";
import Sidebar from "./Sidebar";
import ProductDetails from "./ProductDetails";
import NotFound from "./NotFound";
import "../css/home.css";

const Home = () => {
    return (
        <div className="row home">
            <aside className="col-2 sidebar">
                <Sidebar/>
            </aside>
            <div className="col-10 content">
                <Routes>
                    <Route path="/" element={<AllProducts />}/>
                    <Route path="/category/:id" element={<ProductCategory />}/>
                    <Route path="/product/:id" element={<ProductDetails />}/>
                    <Route path="*" element={<NotFound />}/>
                </Routes>
            </div>
        </div>
     );
}
 
export default Home;