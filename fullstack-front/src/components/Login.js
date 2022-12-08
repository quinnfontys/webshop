import { useState } from 'react';
import url from '../api/url';
import usePost from '../hooks/usePost'; 

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [successMsg, setSuccessMsg] = useState('');

    const user = {email, password};

    const useSubmit = (e) => {
        const response = usePost(e, url.user + "/login", user);

        if (response?.status === 201) {
            setSuccessMsg('Check your mail inbox!');
          }
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
                        placeholder="e.g. quinnfontys@gmail.com"
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="col-12">
                    <label htmlFor="inputPassword" className="form-label">Password</label>
                    <input 
                        type="text" 
                        className="form-control" 
                        id="inputPassword"
                        placeholder="e.g. password123"
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div className="col-12">
                    <button onClick={useSubmit} type="submit" className="btn btn-primary">Add product</button>
                </div>
            </form>
        </section>
    )
}
 
export default Login;