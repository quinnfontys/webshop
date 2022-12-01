import axios from "../api/axios";

const usePost = async (e, url, data) => {
    try {
        e.preventDefault();
        
        console.log(data);
        console.log(url);
        
        const response = await axios.post(url, data, 
        {
            headers: { 'Content-Type': 'application/JSON' }
            // withCredentials: false //?
        });
        
        console.log(response?.data);
        
        if (response?.status === 200) {
            console.log('successfully added data');
        }   
    } catch (err) {
        if (!err?.response) {
            console.log('No server response');
        } else if (err.response?.status === 400) {
            console.log('Unathorized');
        } else {
            console.log('failed');
        }
    }
}

export default usePost;