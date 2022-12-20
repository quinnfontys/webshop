import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import Cookies from "js-cookie";

const Logout = () => {
    let navigate = useNavigate();

    Cookies.remove('email', { path: '' });
    Cookies.remove('JWT', { path: '' });

    useEffect(() => {
        return navigate("/");
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[]);
}
 
export default Logout;