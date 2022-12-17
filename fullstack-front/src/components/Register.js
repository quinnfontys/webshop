import { useState } from 'react';
import url from '../api/url';
import axios from '../api/axios';
import { Link } from 'react-router-dom';

const Register = () => {
    const [email, setEmail] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [successMsg, setSuccessMsg] = useState('');

    const user = {email};

    const useSubmit = (e) => {
        const postData = async () => {
            try {
                e.preventDefault();
                const response = await axios.post(url.user + "/register", user);
                console.log(response);
                console.log(response?.status);
                if (response?.status === 201){
                    setSuccessMsg("successfully registered");
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
            <h1>Register</h1>

            <form className="row g-3">
                <div className="col-12">
                    <label htmlFor="inputEmail" className="form-label">Email</label>
                    <input 
                        type="text" 
                        required
                        className="form-control"
                        id="inputName"
                        placeholder="quinnfontys@gmail.com"
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="col-12">
                    <button onClick={useSubmit} type="submit" className="btn btn-primary">Register</button>
                </div>
            </form>
            <br/><Link className="navbar-brand" to="/login">Login instead</Link>
        </section>
    )
}
 
export default Register;