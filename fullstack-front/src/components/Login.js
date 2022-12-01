import { useState } from 'react';
import url from '../api/url';
import usePost from '../hooks/usePost'; 

const Login = () => {
    const [name] = useState("a name");
    const [categoryId] = useState(2);
    const product = {name, categoryId};

    const useSubmit = (e) => {
        usePost(e, url.product, product);
    }

    return (
        <div>
            <form className="row g-3">
                <div className="col-12">
                    <label htmlFor="inputEmail" className="form-label">Name</label>
                    <input type="text" required className="form-control" id="inputName"/>
                </div>
                <div className="col-12">
                    <label htmlFor="inputPassword" className="form-label">CategoryId</label>
                    <input type="text" className="form-control" id="inputCategoryId"/>
                </div>
                <div className="col-12">
                    <button onClick={useSubmit} type="submit" className="btn btn-primary">Add product</button>
                </div>
            </form>
        </div>
    )
}
 
export default Login;