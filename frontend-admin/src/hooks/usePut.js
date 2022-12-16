import axios from '../api/axios';

const usePut = async (e, url, data) => {
    console.log(" use put called");
    // const [loading, setLoading] = useState(true);
    // const [error, setError] = useState(null);
    const response = null;
    const putData = () => {
        try {
            e.preventDefault()
            
            // console.log(JSON.stringify(data));
            // console.log(data);
            
            // const response = await axios.put(url, data);
             axios.put(url, data);
            console.log(response);
                
            if (response?.status === 200) {
                // setError(null);
                // console.log('Successfully updated data');
            }   
        } catch (err) {
            // setError(err.message);
        } finally {
            // setLoading(false);
        }
    };
    await putData();  

}
 
export default usePut;