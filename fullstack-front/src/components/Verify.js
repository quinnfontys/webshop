import { useEffect, useState } from "react";
import url from "../api/url";
import axios from "../api/axios";

const Verify = () => {
    const email = (new URLSearchParams(window.location.search)).get("email");
    const token = (new URLSearchParams(window.location.search)).get("accessCode");
    // const [error, setError] = useState();
    const [loading, setLoading] = useState(true);
    const [authorized, setAuthorized] = useState(false);
    const [password, setPassword] = useState();
    const [errMsg, setErrMsg] = useState();
    const [successMsg, setSuccessMsg] = useState();
    const [user, setUser] = useState({});



    useEffect(() => {
        const user = {email, password : token};
        const postData = async () => {
            try {
                const response = await axios.post(url.user + "/login", user);
                console.log(response);
                console.log(response?.status);
                if (response?.status === 200){
                    setUser(response.data);
                    setAuthorized(true);
                    //successfully logged in
                }
            } catch (err) {
                setErrMsg(err.message);
                console.log(err.message);
            } finally {
                setLoading(false);
            }
        };
        postData();  
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);


    const useSubmit = (e) => {
        const id = JSON.stringify(user.id);
        const data = {id, password};

        const putData = async () => {
            try {
                e.preventDefault();
                const response = await axios.put(url.user, data);
                console.log(response);
                console.log(response?.status);
                if (response?.status === 200){
                    console.log("successfully updated password");
                    setSuccessMsg("successfully updated password");
                    setErrMsg(null);
                }
            } catch (err) {
                setErrMsg(err.message);
                console.log(err.message);
            }
        };
        putData();  
    }

    if (loading) return (<h3>Loading...</h3>);
    if (errMsg != null) return (<p className="error-msg" aria-live="assertive">{errMsg}</p>);
    if (!authorized) return (<p className="error-msg" aria-live="assertive">This link is not valid</p>);
    return (
        <div>
            <div>
                <p>id: {email}</p>
                <p>token: {token}</p>
            </div>
        <section className="form-section">
            <p className={errMsg ? "error-msg" : "offscreen"} aria-live="assertive">{errMsg}</p>
            <p className={successMsg ? "success-msg" : "offscreen"} aria-live="assertive">{successMsg}</p>
            <h1>Enter password</h1>

            <form className="row g-3">
                <div className="col-6">
                    <label htmlFor="inputEmail" className="form-label">Password</label>
                    <input 
                        type="text" 
                        required
                        className="form-control"
                        id="inputPassword"
                        placeholder="password"
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div className="col-12">
                    <button onClick={useSubmit} type="submit" className="btn btn-primary">Update password</button>
                </div>
            </form>
        </section>


        </div>
    );
}
 
export default Verify;