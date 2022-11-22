import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import Navbar from "./layout/Navbar";
import productDisplay from "./overview/productDisplay";

function App() {
  return (
    <div className="App">
      <Navbar/>

      <p> {Math.random() }</p>

      <productDisplay/>
    </div>
  );
}

export default App;
