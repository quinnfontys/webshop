import axios from '../api/axios';

const usePost = async (e, url, data) => {
    try {
        e.preventDefault();
        
        console.log(JSON.stringify(data));
        console.log(data);
        
        const response = await axios.post(url, data, 
        {
            headers: { 'Content-Type': 'application/JSON' }
        });
        
        console.log(response?.data);
        
        if (response?.status === 200) {
            console.log('Successfully added data');
        }   
    } catch (err) {
        console.log(err);
        if (!err?.response) {
            console.log('No server response');
        } else if (err.response?.status === 400) {
            console.log('Unathorized');
        } else {
            console.log('Failed');
        }
    }
}
 
export default usePost;