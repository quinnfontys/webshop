import { Routes, Route } from "react-router-dom";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/bootstrap/dist/js/bootstrap.bundle";
import "../node_modules/bootstrap-icons/font/bootstrap-icons.css";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import Cart from "./components/Cart";
import Login from "./components/Login";
import Register from "./components/Register";
import Verify from "./components/Verify";

function App() {
  return (
      <div className="App">
        <div className="header">
          <Navbar />
        </div>
        <Routes>
          <Route path="*" element={<Home/>}/>
          <Route path="/cart" element={<Cart/>}></Route>
          <Route path="/login" element={<Login/>}></Route>
          <Route path="/register" element={<Register/>}></Route>
          <Route path="/verify" element={<Verify />} />
        </Routes>
      </div>
  );
}

export default App;