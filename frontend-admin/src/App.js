import { Routes, Route } from "react-router-dom";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/bootstrap/dist/js/bootstrap.bundle";
import "../node_modules/bootstrap-icons/font/bootstrap-icons.css";
import './App.css';
import Navbar from './components/Navbar'
import ProductList from './components/product/List'
import CategoryList from './components/category/List'
import Chatroom from "./components/Chatroom";

function App() {
  return (
    <div className="App">
        <div className="header">
          <Navbar />
        </div>
        <Routes>
          <Route path="/products" element={<ProductList/>}/>
          <Route path="/categories" element={<CategoryList/>}/>
          <Route path="/chat" element={<Chatroom/>}/>
        </Routes>
    </div>
  );
}

export default App;
