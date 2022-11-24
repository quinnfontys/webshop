import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/bootstrap/dist/js/bootstrap.bundle";
import Navbar from "./layout/Navbar";
import Home from "./layout/Home";
import Sidebar from "./layout/Sidebar";

function App() {
  return (
    <div className="App">
      <header className="header">
        <Navbar/>
      </header>
      <div className="content">
        <Home/>
      </div>
      <aside className="sidebar">
        <Sidebar/>
      </aside>
    </div>
  );
}

export default App;
