import { useState } from 'react';
import url from '../api/url';
import { Link } from 'react-router-dom';
import axios from '../api/axios';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [successMsg, setSuccessMsg] = useState('');

    const user = {email, password};

    const useSubmit = (e) => {
        const postData = async () => {
            try {
                e.preventDefault();
                const response = await axios.post(url.user + "/login", user);
                console.log(response);
                console.log(response?.status);
                if (response?.status === 201){
                    setSuccessMsg("successfully logged in");
                }
            } catch (err) {
                setErrMsg(err.message);
                console.log(err.message);
            }
        };
        postData();
    }

    return (
        <section className="form-section">
            <p className={errMsg ? "error-msg" : "offscreen"} aria-live="assertive">{errMsg}</p>
            <p className={successMsg ? "success-msg" : "offscreen"} aria-live="assertive">{successMsg}</p>
            <h1>Log in</h1>

            <form className="row g-3">
                <div className="col-12">
                    <label htmlFor="inputEmail" className="form-label">Email</label>
                    <input 
                        type="text" 
                        required
                        className="form-control"
                        id="inputName"
                        placeholder="Test@example.com"
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="col-12">
                    <label htmlFor="inputPassword" className="form-label">Password</label>
                    <input 
                        type="text" 
                        className="form-control" 
                        id="inputPassword"
                        placeholder="Test"
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div className="col-12">
                    <button onClick={useSubmit} type="submit" className="btn btn-primary">Log in</button>
                </div>
            </form>
            <br/><Link className="navbar-brand" to="/register">Register instead</Link>
        </section>
    )
}
 
export default Login;