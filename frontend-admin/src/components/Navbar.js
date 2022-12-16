import {Link} from 'react-router-dom';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const Navigationbar = () => {
    return ( 
        <Navbar bg="light" expand="lg">
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse>
                <Nav className="me-auto">
                    <Link className="nav-link" to="/products">Products</Link>
                    <Link className="nav-link" to="/categories">Categories</Link>
                    <Link className="nav-link" to="/chat">Chat for some peculiar reason</Link>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
     );
}
 
export default Navigationbar;